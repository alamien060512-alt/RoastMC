package com.roastmc.fabric.injected.world.level.chunk.storage;

import com.roastmc.fabric.util.ContextStateException;
import java.io.IOException;
import net.minecraft.world.level.ChunkPos;

public interface ContextRegionFileStorage {

    default boolean chunkExists(ChunkPos pos) throws IOException {
        throw new ContextStateException("Not implemented");
    }
}
