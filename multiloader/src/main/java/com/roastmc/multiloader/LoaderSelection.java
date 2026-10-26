package com.roastmc.multiloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Decides which edition to start from the {@code neoforge}, {@code fabric} and {@code paper}
 * flags in server.properties.
 *
 * <pre>
 * neoforge  fabric  result
 * true      false   NeoForge edition (Paper/Purpur API)
 * false     true    Fabric edition (Spigot API)
 * true      true    NeoForge edition + Sinytra Connector, so Fabric mods load too
 * false     false   error
 * </pre>
 *
 * Fabric and NeoForge cannot share a JVM, so "both" means Fabric mods translated onto NeoForge.
 */
public record LoaderSelection(Edition edition, boolean connector, List<String> warnings) {

    public enum Edition {
        NEOFORGE("neoforge", "NeoForge (Paper/Purpur API)"),
        FABRIC("fabric", "Fabric (Spigot API)");

        public final String id;
        public final String description;

        Edition(String id, String description) {
            this.id = id;
            this.description = description;
        }
    }

    public static final String KEY_NEOFORGE = "neoforge";
    public static final String KEY_FABRIC = "fabric";
    public static final String KEY_PAPER = "paper";

    public static LoaderSelection from(Properties properties) {
        boolean neoforge = flag(properties, KEY_NEOFORGE, true);
        boolean fabric = flag(properties, KEY_FABRIC, false);
        boolean paper = flag(properties, KEY_PAPER, true);
        List<String> warnings = new ArrayList<>();

        if (!neoforge && !fabric) {
            throw new IllegalArgumentException("Neither 'neoforge' nor 'fabric' is enabled in server.properties. "
                    + "Set at least one of them to true. (Standalone Paper is not bundled: the Paper API comes with the NeoForge edition.)");
        }
        if (neoforge) {
            if (!paper) {
                warnings.add("paper=false is ignored: the Paper/Purpur API is built into the NeoForge edition and cannot be disabled.");
            }
            return new LoaderSelection(Edition.NEOFORGE, fabric, warnings);
        }
        if (paper && properties.containsKey(KEY_PAPER)) {
            warnings.add("paper=true has no effect with the Fabric edition, which uses the Spigot API. Enable neoforge=true for the Paper/Purpur API.");
        }
        return new LoaderSelection(Edition.FABRIC, false, warnings);
    }

    private static boolean flag(Properties properties, String key, boolean def) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            return def;
        }
        return switch (value.trim().toLowerCase()) {
            case "true" -> true;
            case "false" -> false;
            default -> throw new IllegalArgumentException("Invalid value '" + value + "' for '" + key + "' in server.properties (expected true or false).");
        };
    }
}
