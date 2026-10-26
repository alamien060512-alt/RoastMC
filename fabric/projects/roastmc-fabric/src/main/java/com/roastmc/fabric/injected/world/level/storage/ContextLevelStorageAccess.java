package com.roastmc.fabric.injected.world.level.storage;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;

public interface ContextLevelStorageAccess {

    default ResourceKey<LevelStem> roastmc$getTypeKey() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setDimType(ResourceKey<LevelStem> typeKey) {
        throw new ContextStateException("Not implemented");
    }
}
