package com.roastmc.fabric.injected.server.network;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextServerConnectionListener {

    default void acceptConnections() {
        throw new ContextStateException("Not implemented");
    }
}
