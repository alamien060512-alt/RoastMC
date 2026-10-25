package com.roastmc.fabric.injected.server;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.server.dedicated.DedicatedServer;

public interface ContextDedicatedServer {

    default boolean roastmc$bukkitInit() {
        throw new ContextStateException("Not implemented");
    }

    default DedicatedServer self() {
        throw new ContextStateException("Not implemented");
    }
}
