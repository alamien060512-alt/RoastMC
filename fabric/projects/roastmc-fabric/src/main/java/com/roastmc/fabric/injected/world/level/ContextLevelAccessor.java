package com.roastmc.fabric.injected.world.level;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextLevelAccessor {
    default net.minecraft.server.level.ServerLevel getMinecraftWorld() {
        throw new ContextStateException("Not implemented");
    }
}
