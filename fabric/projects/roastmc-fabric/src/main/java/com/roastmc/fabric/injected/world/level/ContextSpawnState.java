package com.roastmc.fabric.injected.world.level;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.MobCategory;

public interface ContextSpawnState {

    default boolean canSpawnForCategory(MobCategory mobCategory, int limit) {
        throw new ContextStateException("Not implemented");
    }
}
