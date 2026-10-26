package com.roastmc.fabric.injected.world.level.levelgen.structure.templatesystem;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.craftbukkit.persistence.CraftPersistentDataContainer;

public interface ContextStructureTemplate {

    default CraftPersistentDataContainer roastmc_persistentDataContainer() {
        throw new ContextStateException("Not implemented");
    }
}
