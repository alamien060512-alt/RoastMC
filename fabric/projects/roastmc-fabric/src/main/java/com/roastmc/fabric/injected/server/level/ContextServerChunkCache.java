package com.roastmc.fabric.injected.server.level;

import com.roastmc.fabric.util.ContextStateException;
import java.io.IOException;

public interface ContextServerChunkCache {

    default boolean isChunkLoaded(int chunkX, int chunkZ) {
        throw new ContextStateException("Not implemented");
    }

    default void close(boolean save) throws IOException {
        throw new ContextStateException("Not implemented");
    }

    default void purgeUnload() {
        throw new ContextStateException("Not implemented");
    }
}
