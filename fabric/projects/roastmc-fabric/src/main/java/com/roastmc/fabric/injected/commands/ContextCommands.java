package com.roastmc.fabric.injected.commands;

import com.roastmc.fabric.util.ContextStateException;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;

public interface ContextCommands {

    default void dispatchServerCommand(CommandSourceStack sender, String command) {
        throw new ContextStateException("Not implemented");
    }

    default void performPrefixedCommand(CommandSourceStack sender, String command, String label) {
        throw new ContextStateException("Not implemented");
    }

    default void performCommand(ParseResults<CommandSourceStack> command, String commandString, String label) {
        throw new ContextStateException("Not implemented");
    }
}
