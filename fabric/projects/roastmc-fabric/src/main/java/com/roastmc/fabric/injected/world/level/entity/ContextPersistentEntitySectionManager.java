package com.roastmc.fabric.injected.world.level.entity;

import com.roastmc.fabric.util.ContextStateException;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;

public interface ContextPersistentEntitySectionManager {

    default List<Entity> getEntities(ChunkPos chunkCoordIntPair) {
        throw new ContextStateException("Not implemented");
    }

    default boolean isPending(long pair) {
        throw new ContextStateException("Not implemented");
    }

    default boolean storeChunkSections(long i, Consumer consumer, boolean callEvent) {
        throw new ContextStateException("Not implemented");
    }

    default void close(boolean save) throws IOException {
        throw new ContextStateException("Not implemented");
    }
}
