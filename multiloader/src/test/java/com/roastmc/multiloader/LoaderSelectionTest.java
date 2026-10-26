package com.roastmc.multiloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;
import org.junit.jupiter.api.Test;

class LoaderSelectionTest {

    private static Properties props(String... kv) {
        Properties p = new Properties();
        for (int i = 0; i < kv.length; i += 2) {
            p.setProperty(kv[i], kv[i + 1]);
        }
        return p;
    }

    @Test
    void defaultsToNeoForge() {
        var s = LoaderSelection.from(props());
        assertEquals(LoaderSelection.Edition.NEOFORGE, s.edition());
        assertFalse(s.connector());
        assertTrue(s.warnings().isEmpty());
    }

    @Test
    void fabricOnly() {
        var s = LoaderSelection.from(props("neoforge", "false", "fabric", "true", "paper", "false"));
        assertEquals(LoaderSelection.Edition.FABRIC, s.edition());
        assertFalse(s.connector());
        assertTrue(s.warnings().isEmpty());
    }

    @Test
    void bothMeansConnector() {
        var s = LoaderSelection.from(props("neoforge", "true", "fabric", "true", "paper", "true"));
        assertEquals(LoaderSelection.Edition.NEOFORGE, s.edition());
        assertTrue(s.connector());
    }

    @Test
    void paperCannotBeDisabledOnNeoForge() {
        var s = LoaderSelection.from(props("neoforge", "true", "paper", "false"));
        assertEquals(1, s.warnings().size());
    }

    @Test
    void paperWithFabricWarns() {
        var s = LoaderSelection.from(props("neoforge", "false", "fabric", "true", "paper", "true"));
        assertEquals(LoaderSelection.Edition.FABRIC, s.edition());
        assertEquals(1, s.warnings().size());
    }

    @Test
    void nothingEnabledFails() {
        assertThrows(IllegalArgumentException.class, () -> LoaderSelection.from(props("neoforge", "false", "fabric", "false")));
        assertThrows(IllegalArgumentException.class, () -> LoaderSelection.from(props("neoforge", "false", "paper", "true")));
    }

    @Test
    void invalidValueFails() {
        assertThrows(IllegalArgumentException.class, () -> LoaderSelection.from(props("fabric", "yes")));
    }

    @Test
    void valuesAreCaseInsensitive() {
        assertTrue(LoaderSelection.from(props("fabric", " TRUE ")).connector());
    }
}
