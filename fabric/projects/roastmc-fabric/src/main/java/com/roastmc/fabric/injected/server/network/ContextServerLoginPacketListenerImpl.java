package com.roastmc.fabric.injected.server.network;

import com.roastmc.fabric.util.ContextStateException;
import com.mojang.authlib.GameProfile;

public interface ContextServerLoginPacketListenerImpl {

    default void disconnect(String s) {
        throw new ContextStateException("Not implemented");
    }

    default void callPlayerPreLoginEvents(GameProfile gameprofile) throws Exception {
        throw new ContextStateException("Not implemented");
    }
}
