package com.roastmc.fabric.mixin.vanilla.server.commands;

import net.minecraft.server.commands.ScheduleCommand;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ScheduleCommand.class)
public class MixinScheduleCommand {

    @Redirect(method = "schedule", at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;getServer()Lnet/minecraft/server/MinecraftServer;"))
    private static ServerLevel roastmc_getLevel(net.minecraft.commands.CommandSourceStack stack) {
        return stack.getLevel(); // CraftBukkit - SPIGOT-6667: Use world specific function timer
    }
}
