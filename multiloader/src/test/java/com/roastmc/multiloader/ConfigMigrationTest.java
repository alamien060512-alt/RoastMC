package com.roastmc.multiloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ConfigMigrationTest {

    @Test
    void movesOldFilesIntoConfig(@TempDir Path dir) throws Exception {
        Files.writeString(dir.resolve("bukkit.yml"), "b");
        Files.writeString(dir.resolve("spigot.yml"), "s");
        Files.createDirectories(dir.resolve("roastmc-config/menu"));
        Files.writeString(dir.resolve("roastmc-config/roastmc.yml"), "r");
        Files.writeString(dir.resolve("roastmc-config/menu/example.yml"), "m");

        List<String> moved = ConfigMigration.migrate(dir, m -> { });

        assertEquals(3, moved.size());
        assertEquals("b", Files.readString(dir.resolve("config/bukkit.yml")));
        assertEquals("s", Files.readString(dir.resolve("config/spigot.yml")));
        assertEquals("r", Files.readString(dir.resolve("config/roastmc/roastmc.yml")));
        assertEquals("m", Files.readString(dir.resolve("config/roastmc/menu/example.yml")));
        assertFalse(Files.exists(dir.resolve("bukkit.yml")));
        assertFalse(Files.exists(dir.resolve("roastmc-config")));
    }

    @Test
    void neverOverwritesAndIsIdempotent(@TempDir Path dir) throws Exception {
        Files.createDirectories(dir.resolve("config"));
        Files.writeString(dir.resolve("config/bukkit.yml"), "new");
        Files.writeString(dir.resolve("bukkit.yml"), "old");

        assertTrue(ConfigMigration.migrate(dir, m -> { }).isEmpty());
        assertEquals("new", Files.readString(dir.resolve("config/bukkit.yml")));
        assertEquals("old", Files.readString(dir.resolve("bukkit.yml")), "left alone when the target exists");
        assertTrue(ConfigMigration.migrate(dir, m -> { }).isEmpty());
    }

    @Test
    void freshServerIsUntouched(@TempDir Path dir) throws Exception {
        assertTrue(ConfigMigration.migrate(dir, m -> { }).isEmpty());
        assertFalse(Files.exists(dir.resolve("config")));
    }
}
