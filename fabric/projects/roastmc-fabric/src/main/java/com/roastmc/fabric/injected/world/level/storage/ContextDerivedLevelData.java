package com.roastmc.fabric.injected.world.level.storage;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;

public interface ContextDerivedLevelData {

    default void setDimType(ResourceKey<LevelStem> typeKey) {
        throw new ContextStateException("Not implemented");
    }
}
