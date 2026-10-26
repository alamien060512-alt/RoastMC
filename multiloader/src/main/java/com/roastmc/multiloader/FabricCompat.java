package com.roastmc.multiloader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Makes Fabric API (via Sinytra Connector) tolerate RoastMC's patched Minecraft.
 *
 * Fabric API's mixins are written for vanilla. RoastMC restructures some vanilla methods for Bukkit, so some
 * mixins cannot attach. Two measures, applied to Fabric jars in mods/ before each start:
 * <ol>
 * <li>mixin configs get {@code defaultRequire: 0}, so an injection that finds no target is skipped instead of
 * aborting startup;</li>
 * <li>mixins known (or discovered by a failed start, see {@link #diagnose}) to be unable to apply at all are removed
 * from their config. Only that one feature of the Fabric API module is lost.</li>
 * </ol>
 * Mods that rewrite large parts of vanilla (for example Lithium) are not made to work by this.
 */
public final class FabricCompat {

    public record Prune(String config, String mixin) {
    }

    static final String MARKER = "META-INF/roastmc-compat.txt";
    private static final Gson PRETTY = new GsonBuilder().setPrettyPrinting().create();
    private static final Pattern[] FAILURES = {
            Pattern.compile("Mixin \\[(\\S+?\\.json):(\\S+?) from mod \\S+?\\] from phase .*?FAILED during APPLY"),
            Pattern.compile("Mixin apply for mod \\S+ failed (\\S+?\\.json):(\\S+?) from mod"),
            Pattern.compile(" in (\\S+?\\.json):(\\S+?) from mod \\S+ failed injection check"),
    };

    private FabricCompat() {
    }

    /** The mixin named in a startup failure, or null if the log shows none. */
    public static Prune diagnose(String log) {
        String plain = log.replaceAll("\u001B\\[[0-9;?]*[A-Za-z]", "");
        Prune first = null;
        int firstAt = Integer.MAX_VALUE;
        for (Pattern pattern : FAILURES) {
            Matcher m = pattern.matcher(plain);
            if (m.find() && m.start() < firstAt) {
                firstAt = m.start();
                first = new Prune(m.group(1), m.group(2));
            }
        }
        return first;
    }

    /** Built-in list plus whatever earlier failed starts discovered. */
    public static Set<Prune> loadPrunes(Path stateFile) throws IOException {
        Set<Prune> prunes = new LinkedHashSet<>();
        try (InputStream in = FabricCompat.class.getResourceAsStream("/fabric-compat-known.json")) {
            if (in != null) {
                readPrunes(new InputStreamReader(in, StandardCharsets.UTF_8), prunes);
            }
        }
        if (Files.exists(stateFile)) {
            try (Reader reader = Files.newBufferedReader(stateFile, StandardCharsets.UTF_8)) {
                readPrunes(reader, prunes);
            }
        }
        return prunes;
    }

    private static void readPrunes(Reader reader, Set<Prune> into) {
        JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
        if (root.has("pruned")) {
            for (JsonElement e : root.getAsJsonArray("pruned")) {
                JsonObject o = e.getAsJsonObject();
                into.add(new Prune(o.get("config").getAsString(), o.get("mixin").getAsString()));
            }
        }
    }

    public static void savePrunes(Path stateFile, Set<Prune> prunes) throws IOException {
        Set<Prune> known = new LinkedHashSet<>();
        try (InputStream in = FabricCompat.class.getResourceAsStream("/fabric-compat-known.json")) {
            if (in != null) {
                readPrunes(new InputStreamReader(in, StandardCharsets.UTF_8), known);
            }
        }
        JsonArray array = new JsonArray();
        for (Prune p : prunes) {
            if (!known.contains(p)) {
                JsonObject o = new JsonObject();
                o.addProperty("config", p.config());
                o.addProperty("mixin", p.mixin());
                array.add(o);
            }
        }
        JsonObject root = new JsonObject();
        root.addProperty("comment", "Fabric API mixins RoastMC removed after a failed start (added automatically)");
        root.add("pruned", array);
        Files.createDirectories(stateFile.toAbsolutePath().getParent());
        Files.writeString(stateFile, PRETTY.toJson(root), StandardCharsets.UTF_8);
    }

    /** Applies the measures to every Fabric jar in {@code modsDir}; returns the jars that were rewritten. */
    public static List<Path> prepare(Path modsDir, Set<Prune> prunes, Consumer<String> log) throws IOException {
        List<Path> changed = new ArrayList<>();
        if (!Files.isDirectory(modsDir)) {
            return changed;
        }
        String marker = marker(prunes);
        List<Path> jars;
        try (Stream<Path> list = Files.list(modsDir)) {
            jars = list.filter(p -> p.getFileName().toString().endsWith(".jar")).sorted().collect(Collectors.toList());
        }
        for (Path jar : jars) {
            if (!isFabricOrigin(jar) || marker.equals(readMarker(jar))) {
                continue;
            }
            byte[] rewritten = rewrite(Files.readAllBytes(jar), prunes, 0);
            byte[] withMarker = addMarker(rewritten != null ? rewritten : Files.readAllBytes(jar), marker);
            Path tmp = jar.resolveSibling(jar.getFileName() + ".compat");
            Files.write(tmp, withMarker);
            Files.move(tmp, jar, StandardCopyOption.REPLACE_EXISTING);
            changed.add(jar);
            // Connector keeps a transformed copy of each Fabric jar; make sure it is not reused for the changed jar
            Path parent = modsDir.toAbsolutePath().getParent();
            if (parent != null) {
                Files.deleteIfExists(parent.resolve(".cache").resolve("connector").resolve(jar.getFileName().toString()));
            }
            log.accept("Adapted " + jar.getFileName() + " for RoastMC");
        }
        return changed;
    }

    static String marker(Set<Prune> prunes) {
        String keys = prunes.stream().map(p -> p.config() + ":" + p.mixin()).sorted().collect(Collectors.joining(","));
        return "v1:" + Integer.toHexString(keys.hashCode());
    }

    private static String readMarker(Path jar) throws IOException {
        try (ZipFile zip = new ZipFile(jar.toFile())) {
            ZipEntry entry = zip.getEntry(MARKER);
            if (entry == null) {
                return null;
            }
            try (InputStream in = zip.getInputStream(entry)) {
                return new String(in.readAllBytes(), StandardCharsets.UTF_8).trim();
            }
        }
    }

    /** Fabric mods carry fabric.mod.json; the forgified Fabric API jar bundles the modules as nested jars. */
    static boolean isFabricOrigin(Path jar) throws IOException {
        if (jar.getFileName().toString().startsWith("forgified-fabric-api")) {
            return true;
        }
        try (ZipFile zip = new ZipFile(jar.toFile())) {
            return zip.getEntry("fabric.mod.json") != null;
        }
    }

    /** @return the new jar bytes, or null when nothing needed changing */
    static byte[] rewrite(byte[] data, Set<Prune> prunes, int depth) throws IOException {
        boolean changed = false;
        ByteArrayOutputStream out = new ByteArrayOutputStream(data.length);
        try (ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(data));
             ZipOutputStream zout = new ZipOutputStream(out)) {
            for (ZipEntry entry; (entry = in.getNextEntry()) != null; ) {
                String name = entry.getName();
                if (MARKER.equals(name)) {
                    continue;
                }
                byte[] bytes = entry.isDirectory() ? new byte[0] : in.readAllBytes();
                if (!entry.isDirectory() && name.endsWith(".jar") && depth < 3) {
                    byte[] nested = rewrite(bytes, prunes, depth + 1);
                    if (nested != null) {
                        bytes = nested;
                        changed = true;
                    }
                } else if (!entry.isDirectory() && (name.endsWith(".mixins.json") || name.endsWith(".mixin.json"))) {
                    byte[] adapted = adaptMixinConfig(bytes, name, prunes);
                    if (adapted != null) {
                        bytes = adapted;
                        changed = true;
                    }
                }
                ZipEntry copy = new ZipEntry(name);
                zout.putNextEntry(copy);
                zout.write(bytes);
                zout.closeEntry();
            }
        }
        return changed ? out.toByteArray() : null;
    }

    /** @return the adapted config, or null if it needed no change */
    static byte[] adaptMixinConfig(byte[] json, String entryName, Set<Prune> prunes) {
        JsonObject root;
        try {
            root = JsonParser.parseString(new String(json, StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (RuntimeException e) {
            return null;
        }
        boolean changed = false;
        if (root.has("injectors") && root.get("injectors").isJsonObject()) {
            JsonObject injectors = root.getAsJsonObject("injectors");
            if (injectors.has("defaultRequire") && injectors.get("defaultRequire").getAsInt() > 0) {
                injectors.addProperty("defaultRequire", 0);
                changed = true;
            }
        }
        for (Prune prune : prunes) {
            if (!entryName.equals(prune.config()) && !entryName.endsWith("/" + prune.config())) {
                continue;
            }
            for (String key : new String[] {"mixins", "client", "server"}) {
                if (!root.has(key) || !root.get(key).isJsonArray()) {
                    continue;
                }
                JsonArray kept = new JsonArray();
                for (JsonElement e : root.getAsJsonArray(key)) {
                    String m = e.getAsString();
                    if (m.equals(prune.mixin()) || m.endsWith("." + prune.mixin())) {
                        changed = true;
                    } else {
                        kept.add(e);
                    }
                }
                root.add(key, kept);
            }
        }
        return changed ? PRETTY.toJson(root).getBytes(StandardCharsets.UTF_8) : null;
    }

    private static byte[] addMarker(byte[] jar, String marker) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(jar.length + 64);
        try (ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(jar));
             ZipOutputStream zout = new ZipOutputStream(out)) {
            for (ZipEntry entry; (entry = in.getNextEntry()) != null; ) {
                if (MARKER.equals(entry.getName())) {
                    continue;
                }
                zout.putNextEntry(new ZipEntry(entry.getName()));
                if (!entry.isDirectory()) {
                    in.transferTo(zout);
                }
                zout.closeEntry();
            }
            zout.putNextEntry(new ZipEntry(MARKER));
            zout.write(marker.getBytes(StandardCharsets.UTF_8));
            zout.closeEntry();
        }
        return out.toByteArray();
    }

    /** Deletes a directory tree (used to discard a world half-created by a failed start). */
    static void deleteTree(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            return;
        }
        try (Stream<Path> walk = Files.walk(dir)) {
            for (Path p : walk.sorted(Comparator.reverseOrder()).collect(Collectors.toList())) {
                Files.delete(p);
            }
        }
    }
}
