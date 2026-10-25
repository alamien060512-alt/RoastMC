package com.roastmc.fabric.injected.network;

import com.roastmc.fabric.util.ContextStateException;
import java.net.SocketAddress;

public interface ContextConnection {

    default String roastmc$hostname() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setHostName(String hostName) {
        throw new ContextStateException("Not implemented");
    }

    default SocketAddress getRawAddress() {
        throw new ContextStateException("Not implemented");
    }
}
