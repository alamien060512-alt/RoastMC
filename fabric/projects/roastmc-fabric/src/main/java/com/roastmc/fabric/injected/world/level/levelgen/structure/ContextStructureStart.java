package com.roastmc.fabric.injected.world.level.levelgen.structure;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextStructureStart {

    default org.bukkit.event.world.AsyncStructureGenerateEvent.Cause roastmc_generationEventCause() {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.craftbukkit.persistence.DirtyCraftPersistentDataContainer roastmc_persistentDataContainer() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_setGenerationEventCause(org.bukkit.event.world.AsyncStructureGenerateEvent.Cause generationEventCause) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_setPersistentDataContainer(org.bukkit.craftbukkit.persistence.DirtyCraftPersistentDataContainer persistentDataContainer) {
        throw new ContextStateException("Not implemented");
    }
}
