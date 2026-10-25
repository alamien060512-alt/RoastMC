package com.roastmc.fabric.injected.world.level.chunk;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;

public interface ContextLevelChunkSection {

    default void setBiome(int i, int j, int k, Holder<Biome> biome) {
        throw new ContextStateException("Not implemented");
    }
}
