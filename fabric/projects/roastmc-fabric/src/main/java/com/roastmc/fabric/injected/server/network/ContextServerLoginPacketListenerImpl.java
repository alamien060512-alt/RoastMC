package com.roastmc.fabric.injected.server.network;

import com.roastmc.fabric.util.ContextStateException;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;

public interface ContextServerLoginPacketListenerImpl {

    default void disconnect(String s) {
        throw new ContextStateException("Not implemented");
    }

    default void callPlayerPreLoginEvents(GameProfile gameprofile) throws Exception {
        throw new ContextStateException("Not implemented");
    }

    default Connection roastmc$connection() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setKickMessage(Component component) {
        throw new ContextStateException("Not implemented");
    }

    default Component roastmc$kickMessage() {
        throw new ContextStateException("Not implemented");
    }
}
