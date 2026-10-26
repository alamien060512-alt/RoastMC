package com.roastmc.fabric.mixin.vanilla.server.commands;

import net.minecraft.server.commands.GiveCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(GiveCommand.class)
public class MixinGiveCommand {

    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;drop(Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/item/ItemEntity;"))
    private static ItemEntity roastmc_drop(ServerPlayer player, ItemStack stack, boolean dropAround) {
        return player.drop(stack, dropAround, false, false); // CraftBukkit - SPIGOT-2942: Add boolean to call event
    }
}
