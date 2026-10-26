package com.roastmc.fabric.injected.world.level.chunk;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.server.level.ServerLevel;

public interface ContextLevelChunk {

    default org.bukkit.Chunk getBukkitChunk() {
        throw new ContextStateException("Not implemented");
    }

    default ServerLevel roastmc$r() {
        throw new ContextStateException("Not implemented");
    }

    default void loadCallback() {
        throw new ContextStateException("Not implemented");
    }

    default void unloadCallback() {
        throw new ContextStateException("Not implemented");
    }
}
