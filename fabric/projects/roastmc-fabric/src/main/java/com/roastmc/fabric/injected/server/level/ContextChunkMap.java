package com.roastmc.fabric.injected.server.level;

import com.roastmc.fabric.bukkit.BukkitCallbackExecutor;
import com.roastmc.fabric.util.ContextStateException;

public interface ContextChunkMap {

    default BukkitCallbackExecutor roastmc$callbackExecutor() {
        throw new ContextStateException("Not implemented");
    }
}
