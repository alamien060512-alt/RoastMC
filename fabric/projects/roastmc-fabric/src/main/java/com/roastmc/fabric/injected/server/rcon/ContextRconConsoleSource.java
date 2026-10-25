package com.roastmc.fabric.injected.server.rcon;

import com.roastmc.fabric.util.ContextStateException;
import java.net.SocketAddress;
import net.minecraft.commands.CommandSourceStack;

public interface ContextRconConsoleSource {

    default SocketAddress roastmc$socketAddress() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setSocketAddress(SocketAddress socketAddress) {
        throw new ContextStateException("Not implemented");
    }

    default void sendMessage(String message) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.command.CommandSender getBukkitSender(CommandSourceStack wrapper) {
        throw new ContextStateException("Not implemented");
    }
}
