package com.roastmc.fabric.mixin.vanilla.world.inventory;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FurnaceResultSlot.class)
public class MixinFurnaceResultSlot {

    @Shadow @Final private Container container;
    @Shadow private int removeCount;

    @Redirect(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;awardUsedRecipesAndPopExperience(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private void roastmc$awardUsedRecipesAndPopExperience(AbstractFurnaceBlockEntity entity, ServerPlayer player) {
        entity.awardUsedRecipesAndPopExperience(player);
    }
}
