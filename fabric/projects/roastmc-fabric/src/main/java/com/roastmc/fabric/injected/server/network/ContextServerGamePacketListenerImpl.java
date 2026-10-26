package com.roastmc.fabric.injected.server.network;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.network.chat.PlayerChatMessage;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.slf4j.Logger;

public interface ContextServerGamePacketListenerImpl extends ContextServerCommonPacketListenerImpl {

    default CraftPlayer getCraftPlayer() {
        throw new ContextStateException("Not implemented");
    }

    default void teleport(double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause cause) {
        throw new ContextStateException("Not implemented");
    }


    default void teleport(Location dest) {
        throw new ContextStateException("Not implemented");
    }

    default void chat(String s, PlayerChatMessage original, boolean async) {
        throw new ContextStateException("Not implemented");
    }

    default void handleCommand(String s) {
        throw new ContextStateException("Not implemented");
    }

    default boolean isDisconnected() {
        throw new ContextStateException("Not implemented");
    }

    default boolean checkLimit(long timestamp) {
        throw new ContextStateException("Not implemented");
    }

    default CraftServer roastmc$craftServer() {
        throw new ContextStateException("Not implemented");
    }

    default Logger roastmc$logger() {
        throw new ContextStateException("Not implemented");
    }

    default void pushTeleportCause(PlayerTeleportEvent.TeleportCause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$pushNoTeleportEvent() {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$teleportCancelled() {
        throw new ContextStateException("Not implemented");
    }

    default void detectRateSpam(String s) {
        throw new ContextStateException("Not implemented");
    }
}
