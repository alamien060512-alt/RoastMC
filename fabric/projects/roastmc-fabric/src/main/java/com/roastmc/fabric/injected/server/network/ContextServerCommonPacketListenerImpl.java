package com.roastmc.fabric.injected.server.network;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public interface ContextServerCommonPacketListenerImpl {

    default void disconnect(String s) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$processedDisconnect() {
        throw new ContextStateException("Not implemented");
    }

    default boolean isDisconnected() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setProcessedDisconnect(boolean processedDisconnect) {
        throw new ContextStateException("Not implemented");
    }

    default CraftPlayer getCraftPlayer() {
        throw new ContextStateException("Not implemented");
    }

    default boolean isTransferred() {
        throw new ContextStateException("Not implemented");
    }

    default ConnectionProtocol getProtocol() {
        throw new ContextStateException("Not implemented");
    }

    default void sendPacket(Packet<?> packet) {
        throw new ContextStateException("Not implemented");
    }

    default void kickPlayer(Component reason) {
        throw new ContextStateException("Not implemented");
    }

    default ServerPlayer roastmc$player() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setPlayer(ServerPlayer player) {
        throw new ContextStateException("Not implemented");
    }
}
