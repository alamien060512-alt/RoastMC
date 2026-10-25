package com.roastmc.multiloader;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/** Reads server.properties and adds the RoastMC loader flags without touching anything else in the file. */
public final class ServerProperties {

    private static final String[][] DEFAULTS = {
            {LoaderSelection.KEY_NEOFORGE, "true"},
            {LoaderSelection.KEY_FABRIC, "false"},
            {LoaderSelection.KEY_PAPER, "true"},
    };

    private ServerProperties() {
    }

    public static Properties loadAndEnsureKeys(Path file) throws IOException {
        Properties properties = new Properties();
        if (Files.exists(file)) {
            try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                properties.load(reader);
            }
        }
        StringBuilder missing = new StringBuilder();
        for (String[] entry : DEFAULTS) {
            if (!properties.containsKey(entry[0])) {
                missing.append(entry[0]).append('=').append(entry[1]).append(System.lineSeparator());
                properties.setProperty(entry[0], entry[1]);
            }
        }
        if (!missing.isEmpty()) {
            String prefix = "";
            if (Files.exists(file)) {
                String existing = Files.readString(file, StandardCharsets.UTF_8);
                if (!existing.isEmpty() && !existing.endsWith("\n")) {
                    prefix = System.lineSeparator();
                }
            }
            String header = "# RoastMC loader selection: neoforge+paper, fabric, or neoforge+fabric (Fabric mods via Sinytra Connector)"
                    + System.lineSeparator();
            Files.writeString(file, prefix + header + missing, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        return properties;
    }
}
