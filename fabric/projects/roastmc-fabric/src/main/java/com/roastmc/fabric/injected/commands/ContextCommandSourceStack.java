package com.roastmc.fabric.injected.commands;

import com.roastmc.fabric.util.ContextStateException;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSource;

public interface ContextCommandSourceStack {

    default void roastmc$setSource(CommandSource source) {
        throw new ContextStateException("Not implemented");
    }

    default boolean hasPermission(int i, String bukkitPermission) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.command.CommandSender getBukkitSender() {
        throw new ContextStateException("Not implemented");
    }

    default CommandNode<?> roastmc$currentCommand() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$currentCommand(CommandNode<?> node) {
        throw new ContextStateException("Not implemented");
    }
}
