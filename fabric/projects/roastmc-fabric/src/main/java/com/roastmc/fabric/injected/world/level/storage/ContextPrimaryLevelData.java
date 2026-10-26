package com.roastmc.fabric.injected.world.level.storage;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.LevelStem;

public interface ContextPrimaryLevelData {

    default Registry<LevelStem> roastmc_customDimensions() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_customDimensions(Registry<LevelStem> customDimensions) {
        throw new ContextStateException("Not implemented");
    }

    default void checkName(String name) {
        throw new ContextStateException("Not implemented");
    }

    default void setWorld(ServerLevel world) {
        throw new ContextStateException("Not implemented");
    }
}
