package com.roastmc.fabric.mixin.vanilla.commands;

import com.roastmc.fabric.injected.commands.ContextCommandSource;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(CommandSource.class)
public interface MixinCommandSource extends ContextCommandSource {

    default org.bukkit.command.CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return org.bukkit.craftbukkit.command.NullCommandSender.INSTANCE;
    }
}

