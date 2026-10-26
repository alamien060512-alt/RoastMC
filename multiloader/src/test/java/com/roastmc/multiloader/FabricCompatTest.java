package com.roastmc.multiloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FabricCompatTest {

    private static final String CONFIG = "{\"package\":\"p\",\"mixins\":[\"AMixin\",\"sub.BMixin\"],\"client\":[\"CMixin\"],"
            + "\"injectors\":{\"defaultRequire\":1,\"maxShiftBy\":3}}";

    private static byte[] zip(String[] names, byte[][] data) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (ZipOutputStream z = new ZipOutputStream(out)) {
            for (int i = 0; i < names.length; i++) {
                z.putNextEntry(new ZipEntry(names[i]));
                z.write(data[i]);
                z.closeEntry();
            }
        }
        return out.toByteArray();
    }

    private static String read(byte[] jar, String name) throws Exception {
        try (ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(jar))) {
            for (ZipEntry e; (e = in.getNextEntry()) != null; ) {
                if (e.getName().equals(name)) {
                    return new String(in.readAllBytes(), StandardCharsets.UTF_8);
                }
            }
        }
        return null;
    }

    @Test
    void diagnosesAllThreeFailureFormats() {
        var apply = FabricCompat.diagnose("[04:05:01 ERROR]: Mixin [fabric-x.mixins.json:FooMixin from mod fabric_x] from phase [DEFAULT] in config [fabric-x.mixins.json] FAILED during APPLY");
        assertEquals(new FabricCompat.Prune("fabric-x.mixins.json", "FooMixin"), apply);

        var fatal = FabricCompat.diagnose("[04:05:01 FATAL]: Mixin apply for mod lithium failed lithium.mixins.json:world.raycast.BlockGetterMixin from mod lithium -> net.minecraft.world.level.BlockGetter: x");
        assertEquals(new FabricCompat.Prune("lithium.mixins.json", "world.raycast.BlockGetterMixin"), fatal);

        var inj = FabricCompat.diagnose("Caused by: InjectionError: Critical injection failure: Callback method a()V in fabric-entity-events-v1.mixins.json:effect.LivingEntityMixin from mod fabric_entity_events_v1 failed injection check, (0/1) succeeded.");
        assertEquals(new FabricCompat.Prune("fabric-entity-events-v1.mixins.json", "effect.LivingEntityMixin"), inj);
    }

    @Test
    void diagnosePicksTheEarliestAndIgnoresUnrelatedLogs() {
        assertNull(FabricCompat.diagnose("[INFO]: Done (3s)! For help, type \"help\""));
        assertNull(FabricCompat.diagnose("java.lang.NullPointerException: Cannot read the array length"));
        var p = FabricCompat.diagnose("Mixin apply for mod a failed one.json:A from mod a\nMixin apply for mod b failed two.json:B from mod b");
        assertEquals("A", p.mixin());
    }

    @Test
    void relaxesRequireAndPrunesOnlyTheMatchingConfig() {
        Set<FabricCompat.Prune> prunes = Set.of(new FabricCompat.Prune("x.mixins.json", "BMixin"));
        byte[] out = FabricCompat.adaptMixinConfig(CONFIG.getBytes(StandardCharsets.UTF_8), "x.mixins.json", prunes);
        JsonObject json = JsonParser.parseString(new String(out, StandardCharsets.UTF_8)).getAsJsonObject();
        assertEquals(0, json.getAsJsonObject("injectors").get("defaultRequire").getAsInt());
        assertEquals(3, json.getAsJsonObject("injectors").get("maxShiftBy").getAsInt());
        assertEquals(1, json.getAsJsonArray("mixins").size());
        assertEquals("AMixin", json.getAsJsonArray("mixins").get(0).getAsString());

        byte[] other = FabricCompat.adaptMixinConfig(CONFIG.getBytes(StandardCharsets.UTF_8), "y.mixins.json", prunes);
        JsonObject kept = JsonParser.parseString(new String(other, StandardCharsets.UTF_8)).getAsJsonObject();
        assertEquals(2, kept.getAsJsonArray("mixins").size(), "other configs keep their mixins");
        assertNull(FabricCompat.adaptMixinConfig("{\"mixins\":[]}".getBytes(StandardCharsets.UTF_8), "z.mixins.json", prunes));
    }

    @Test
    void rewritesNestedJars() throws Exception {
        byte[] inner = zip(new String[] {"x.mixins.json", "A.class"}, new byte[][] {CONFIG.getBytes(StandardCharsets.UTF_8), {1, 2}});
        byte[] outer = zip(new String[] {"META-INF/jarjar/x.jar", "top.txt"}, new byte[][] {inner, "hi".getBytes()});
        byte[] result = FabricCompat.rewrite(outer, Set.of(new FabricCompat.Prune("x.mixins.json", "CMixin")), 0);
        assertNotNull(result);
        String nestedConfig = read(read2(result, "META-INF/jarjar/x.jar"), "x.mixins.json");
        assertTrue(nestedConfig.contains("\"defaultRequire\": 0"));
        assertFalse(nestedConfig.contains("CMixin"));
        assertEquals("hi", read(result, "top.txt"));
        assertNull(FabricCompat.rewrite(zip(new String[] {"a.txt"}, new byte[][] {{1}}), Set.of(), 0));
    }

    private static byte[] read2(byte[] jar, String name) throws Exception {
        try (ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(jar))) {
            for (ZipEntry e; (e = in.getNextEntry()) != null; ) {
                if (e.getName().equals(name)) {
                    return in.readAllBytes();
                }
            }
        }
        throw new AssertionError(name);
    }

    @Test
    void prepareAdaptsFabricJarsOnceAndLeavesOthersAlone(@TempDir Path mods) throws Exception {
        byte[] fabricJar = zip(new String[] {"fabric.mod.json", "x.mixins.json"}, new byte[][] {"{}".getBytes(), CONFIG.getBytes(StandardCharsets.UTF_8)});
        byte[] neoJar = zip(new String[] {"META-INF/neoforge.mods.toml", "n.mixins.json"}, new byte[][] {"x".getBytes(), CONFIG.getBytes(StandardCharsets.UTF_8)});
        Files.write(mods.resolve("fab.jar"), fabricJar);
        Files.write(mods.resolve("neo.jar"), neoJar);
        Files.writeString(mods.resolve("notes.txt"), "keep");
        Set<FabricCompat.Prune> prunes = new LinkedHashSet<>(List.of(new FabricCompat.Prune("x.mixins.json", "AMixin")));

        assertEquals(1, FabricCompat.prepare(mods, prunes, m -> { }).size());
        assertTrue(read(Files.readAllBytes(mods.resolve("fab.jar")), "x.mixins.json").contains("\"defaultRequire\": 0"));
        assertEquals(CONFIG, read(Files.readAllBytes(mods.resolve("neo.jar")), "n.mixins.json"), "NeoForge mods are untouched");
        assertEquals("keep", Files.readString(mods.resolve("notes.txt")));

        assertTrue(FabricCompat.prepare(mods, prunes, m -> { }).isEmpty(), "second run is a no-op");
        prunes.add(new FabricCompat.Prune("x.mixins.json", "sub.BMixin"));
        assertEquals(1, FabricCompat.prepare(mods, prunes, m -> { }).size(), "a new prune re-applies");
        String config = read(Files.readAllBytes(mods.resolve("fab.jar")), "x.mixins.json");
        assertFalse(config.contains("AMixin") || config.contains("BMixin"));
    }

    @Test
    void adaptingAJarDropsConnectorsCachedCopy(@TempDir Path dir) throws Exception {
        Path mods = Files.createDirectories(dir.resolve("mods"));
        Path cache = Files.createDirectories(dir.resolve(".cache/connector"));
        byte[] fabricJar = zip(new String[] {"fabric.mod.json", "x.mixins.json"}, new byte[][] {"{}".getBytes(), CONFIG.getBytes(StandardCharsets.UTF_8)});
        Files.write(mods.resolve("fab.jar"), fabricJar);
        Files.writeString(cache.resolve("fab.jar"), "stale");
        Files.writeString(cache.resolve("other.jar"), "keep");
        FabricCompat.prepare(mods, new LinkedHashSet<>(), m -> { });
        assertFalse(Files.exists(cache.resolve("fab.jar")), "stale transformed copy removed");
        assertTrue(Files.exists(cache.resolve("other.jar")), "other cache entries stay");
    }

    @Test
    void stateRoundTripStoresOnlyDiscoveredPrunes(@TempDir Path dir) throws Exception {
        Path state = dir.resolve("config/roastmc/fabric-compat.json");
        Set<FabricCompat.Prune> prunes = FabricCompat.loadPrunes(state);
        int known = prunes.size();
        assertTrue(known >= 12, "built-in list is loaded");
        prunes.add(new FabricCompat.Prune("lithium.mixins.json", "Foo"));
        FabricCompat.savePrunes(state, prunes);
        String saved = Files.readString(state);
        assertTrue(saved.contains("lithium.mixins.json") && !saved.contains("fabric-loot-api-v3"));
        assertEquals(known + 1, FabricCompat.loadPrunes(state).size());
    }

    @Test
    void deleteTreeRemovesEverything(@TempDir Path dir) throws Exception {
        Files.createDirectories(dir.resolve("world/region"));
        Files.writeString(dir.resolve("world/region/r.mca"), "x");
        FabricCompat.deleteTree(dir.resolve("world"));
        assertFalse(Files.exists(dir.resolve("world")));
        FabricCompat.deleteTree(dir.resolve("world"));
    }
}
