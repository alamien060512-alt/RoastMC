package com.roastmc.multiloader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Entry point of the unified RoastMC jar. Reads server.properties, optionally provisions
 * Sinytra Connector, then runs the selected edition in its own JVM (the editions patch
 * Minecraft differently and can never share one).
 */
public final class RoastMCLauncher {

    private static final String LOGO = """
             ____                 _   __  __  ____
            |  _ \\ ___   __ _ ___| |_|  \\/  |/ ___|
            | |_) / _ \\ / _` / __| __| |\\/| | |
            |  _ < (_) | (_| \\__ \\ |_| |  | | |___
            |_| \\_\\___/ \\__,_|___/\\__|_|  |_|\\____|
            """;

    private RoastMCLauncher() {
    }

    /** Editions are built for this Java version (and the game needs it). */
    static final int REQUIRED_JAVA = 25;

    public static void main(String[] args) throws Exception {
        // Keep this check first and free of other classes: this launcher is compiled for an older Java than the editions.
        int java = Runtime.version().feature();
        if (java < REQUIRED_JAVA) {
            System.err.println("[RoastMC] RoastMC needs Java " + REQUIRED_JAVA + " or newer, but this is Java " + java
                    + " (" + System.getProperty("java.home") + ").");
            System.err.println("[RoastMC] Install Java " + REQUIRED_JAVA + " from https://adoptium.net/temurin/releases/?version="
                    + REQUIRED_JAVA + " and make sure 'java -version' shows it, then start again.");
            System.exit(1);
            return;
        }
        System.out.println(LOGO);
        String mc = minecraftVersion();

        LoaderSelection selection;
        try {
            Properties properties = ServerProperties.loadAndEnsureKeys(Path.of("server.properties"));
            selection = LoaderSelection.from(properties);
        } catch (IllegalArgumentException e) {
            System.err.println("[RoastMC] " + e.getMessage());
            System.exit(1);
            return;
        }
        selection.warnings().forEach(w -> System.out.println("[RoastMC] WARNING: " + w));
        System.out.println("[RoastMC] Minecraft " + mc + ", starting the " + selection.edition().description + " edition"
                + (selection.connector() ? " with Fabric mod support (Sinytra Connector)" : ""));

        boolean connectorActive = false;
        if (!selection.connector()) {
            for (String removed : ConnectorInstaller.uninstall(Path.of("mods"))) {
                System.out.println("[RoastMC] Removed " + removed + " (Fabric mod support is off)");
            }
        }
        if (selection.connector()) {
            System.out.println("[RoastMC] NOTE: Fabric mod support (neoforge=true + fabric=true) is experimental. RoastMC changes parts of "
                    + "Minecraft that some Fabric API features hook into, so those features are switched off, and mods that rewrite "
                    + "large parts of vanilla (for example Lithium) will not work. Set fabric=false to turn it off.");
            var result = new ConnectorInstaller(ConnectorInstaller.DEFAULT_API, m -> System.out.println("[RoastMC] " + m))
                    .install(mc, Path.of("mods"));
            System.out.println("[RoastMC] " + (result.installed() ? "" : "WARNING: ") + result.message());
            connectorActive = result.installed();
        }

        if (selection.edition() == LoaderSelection.Edition.NEOFORGE) {
            ConfigMigration.migrate(Path.of("."), m -> System.out.println("[RoastMC] " + m));
        }

        Path jar = extractEdition(selection.edition(), mc);
        List<String> jvmProperties = new ArrayList<>();
        if (connectorActive) {
            // Tells the NeoForge edition's launcher to avoid duplicate library modules (see LaunchArgsParser)
            jvmProperties.add("-Droastmc.connector=true");
        }
        if (selection.edition() == LoaderSelection.Edition.FABRIC) {
            // FML's mods folder is fixed to mods/, but Fabric Loader can be pointed elsewhere
            Path fabricMods = Path.of("mods", "fabric");
            Files.createDirectories(fabricMods);
            jvmProperties.add("-Dfabric.modsFolder=" + fabricMods);
            System.out.println("[RoastMC] Fabric mods go in " + fabricMods + "/");
        }
        if (connectorActive) {
            System.exit(runWithFabricCompat(jar, args, jvmProperties));
        }
        System.exit(run(jar, args, jvmProperties));
    }

    /** Most automatic "remove one incompatible mixin and restart" rounds before giving up. */
    private static final int MAX_COMPAT_ROUNDS = 30;

    /**
     * Runs the NeoForge edition with Fabric support. Fabric jars are adapted first; if startup still fails because
     * one more mixin cannot apply, that mixin is removed (and remembered) and the server is restarted.
     */
    private static int runWithFabricCompat(Path jar, String[] args, List<String> jvmProperties) throws IOException, InterruptedException {
        Path modsDir = Path.of("mods");
        Path state = Path.of("config", "roastmc", "fabric-compat.json");
        java.util.function.Consumer<String> log = m -> System.out.println("[RoastMC] " + m);
        Set<FabricCompat.Prune> prunes = FabricCompat.loadPrunes(state);
        for (int round = 1; ; round++) {
            FabricCompat.prepare(modsDir, prunes, log);
            Path world = Path.of(levelName());
            boolean hadWorld = Files.exists(world);
            long started = System.currentTimeMillis();
            int code = run(jar, args, jvmProperties);
            if (code == 0 || round >= MAX_COMPAT_ROUNDS) {
                return code;
            }
            Path serverLog = Path.of("logs", "latest.log");
            if (!Files.exists(serverLog) || Files.getLastModifiedTime(serverLog).toMillis() < started) {
                return code;
            }
            String text = Files.readString(serverLog, java.nio.charset.StandardCharsets.UTF_8);
            if (text.contains("Done (")) {
                return code; // it ran; this is not a startup incompatibility
            }
            FabricCompat.Prune failing = FabricCompat.diagnose(text);
            if (failing == null || !prunes.add(failing)) {
                return code;
            }
            FabricCompat.savePrunes(state, prunes);
            if (!hadWorld) {
                FabricCompat.deleteTree(world); // do not keep a world half-created by the failed start
            }
            log.accept("Fabric mixin " + failing.mixin() + " (" + failing.config() + ") cannot work with RoastMC's patches. "
                    + "Switching that feature off and restarting (round " + round + ").");
        }
    }

    private static String levelName() {
        try {
            Properties properties = ServerProperties.loadAndEnsureKeys(Path.of("server.properties"));
            return properties.getProperty("level-name", "world");
        } catch (IOException e) {
            return "world";
        }
    }

    static String minecraftVersion() throws IOException {
        try (InputStream in = RoastMCLauncher.class.getResourceAsStream("/roastmc-multiloader.properties")) {
            Properties properties = new Properties();
            if (in != null) {
                properties.load(in);
            }
            return properties.getProperty("minecraft.version", "unknown");
        }
    }

    private static Path extractEdition(LoaderSelection.Edition edition, String mc) throws IOException {
        URL resource = RoastMCLauncher.class.getResource("/META-INF/editions/" + edition.id + ".jar");
        if (resource == null) {
            throw new IllegalStateException("This RoastMC jar does not contain the " + edition.id
                    + " edition. Build it with ./gradlew roastmcAllJar.");
        }
        Path target = Path.of("libraries", "roastmc", edition.id + "-" + mc + ".jar");
        Files.createDirectories(target.getParent());
        long size = resource.openConnection().getContentLengthLong();
        if (!Files.exists(target) || Files.size(target) != size) {
            try (InputStream in = resource.openStream()) {
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        return target;
    }

    private static int run(Path jar, String[] args, List<String> jvmProperties) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add(Path.of(System.getProperty("java.home"), "bin", "java").toString());
        for (String arg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            if (!arg.startsWith("-Djava.class.path")) {
                command.add(arg);
            }
        }
        command.addAll(jvmProperties);
        command.add("-jar");
        command.add(jar.toString());
        command.addAll(List.of(args));

        Process process = new ProcessBuilder(command).inheritIO().start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (process.isAlive()) {
                process.destroy();
                try {
                    if (!process.waitFor(30, java.util.concurrent.TimeUnit.SECONDS)) {
                        process.destroyForcibly();
                    }
                } catch (InterruptedException ignored) {
                    process.destroyForcibly();
                }
            }
        }, "roastmc-edition-shutdown"));
        return process.waitFor();
    }
}
