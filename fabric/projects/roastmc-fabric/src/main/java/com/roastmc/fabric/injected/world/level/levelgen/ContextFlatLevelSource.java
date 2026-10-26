package com.roastmc.fabric.injected.world.level.levelgen;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.level.biome.BiomeSource;

public interface ContextFlatLevelSource {

    default void roastmc$setBiomeSource(BiomeSource biomeSource) {
        throw new ContextStateException("Not implemented");
    }
}
