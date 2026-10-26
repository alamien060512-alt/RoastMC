package com.roastmc.multiloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Keeps all configuration under {@code config/}. Older servers kept {@code bukkit.yml}, {@code spigot.yml},
 * {@code purpur.yml} and {@code commands.yml} in the server folder and RoastMC's own files in
 * {@code roastmc-config/}; move them to where the NeoForge edition now looks, without overwriting anything.
 */
public final class ConfigMigration {

    private static final List<String> ROOT_FILES = List.of("bukkit.yml", "spigot.yml", "purpur.yml", "commands.yml");

    private ConfigMigration() {
    }

    /** @return the paths that were moved, relative to {@code serverDir} (for logging and tests) */
    public static List<String> migrate(Path serverDir, Consumer<String> log) throws IOException {
        List<String> moved = new ArrayList<>();
        Path config = serverDir.resolve("config");

        for (String name : ROOT_FILES) {
            Path old = serverDir.resolve(name);
            Path target = config.resolve(name);
            if (Files.isRegularFile(old) && !Files.exists(target)) {
                Files.createDirectories(config);
                Files.move(old, target);
                moved.add(name + " -> config/" + name);
            }
        }

        Path oldDir = serverDir.resolve("roastmc-config");
        Path newDir = config.resolve("roastmc");
        if (Files.isDirectory(oldDir) && !Files.exists(newDir)) {
            Files.createDirectories(config);
            Files.move(oldDir, newDir);
            moved.add("roastmc-config/ -> config/roastmc/");
        }

        moved.forEach(m -> log.accept("Moved " + m));
        return moved;
    }
}
