package com.roastmc.fabric.injected.commands;

import net.minecraft.commands.CommandSourceStack;

public interface ContextCommandSource {

    default org.bukkit.command.CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return null;
    }
}
