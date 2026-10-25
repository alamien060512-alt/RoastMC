package com.roastmc.multiloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ServerPropertiesTest {

    @Test
    void createsFileWithDefaults(@TempDir Path dir) throws Exception {
        Path file = dir.resolve("server.properties");
        var p = ServerProperties.loadAndEnsureKeys(file);
        assertEquals("true", p.getProperty("neoforge"));
        assertEquals("false", p.getProperty("fabric"));
        assertEquals("true", p.getProperty("paper"));
        assertTrue(Files.readString(file).contains("fabric=false"));
    }

    @Test
    void keepsExistingContentAndValues(@TempDir Path dir) throws Exception {
        Path file = dir.resolve("server.properties");
        Files.writeString(file, "motd=Hi\nfabric=true"); // no trailing newline
        var p = ServerProperties.loadAndEnsureKeys(file);
        assertEquals("true", p.getProperty("fabric"));
        String text = Files.readString(file);
        assertTrue(text.startsWith("motd=Hi\nfabric=true"));
        assertTrue(text.contains("neoforge=true"));
        assertEquals(1, text.split("fabric=", -1).length - 1);
        // idempotent
        ServerProperties.loadAndEnsureKeys(file);
        assertEquals(text, Files.readString(file));
    }
}
