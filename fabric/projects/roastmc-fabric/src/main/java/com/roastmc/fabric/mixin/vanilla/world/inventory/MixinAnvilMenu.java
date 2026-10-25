package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextAnvilMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(AnvilMenu.class)
public class MixinAnvilMenu implements ContextAnvilMenu {

    // CraftBukkit start
    @Unique
    public static final int DEFAULT_DENIED_COST = -1;

    @Unique
    public int maximumRepairCost = 40;
    // CraftBukkit end

    @Override
    public int roastmc$getDeniedCost() {
        return DEFAULT_DENIED_COST;
    }

    @Override
    public int roastmc$maximumRepairCost() {
        return maximumRepairCost;
    }

    @Override
    public void roastmc$maximumRepairCost(int maximumRepairCost) {
        this.maximumRepairCost = maximumRepairCost;
    }

    @Shadow
    private net.minecraft.world.inventory.DataSlot cost;

    @Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
    private void roastmc$mayPickup(Player player, boolean hasItem, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((player.hasInfiniteMaterials() || player.experienceLevel >= this.cost.get()) && this.cost.get() > DEFAULT_DENIED_COST && hasItem);
    }

    @Redirect(method = {"createResult", "onTake"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/DataSlot;set(I)V"))
    private void roastmc$setCost(net.minecraft.world.inventory.DataSlot dataSlot, int value) {
        dataSlot.set(value == 0 ? DEFAULT_DENIED_COST : value);
    }

    @Redirect(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private void roastmc$setItem(net.minecraft.world.inventory.ResultContainer resultContainer, int slot, net.minecraft.world.item.ItemStack itemStack) {
        org.bukkit.craftbukkit.event.CraftEventFactory.callPrepareAnvilEvent((org.bukkit.inventory.view.AnvilView) ((AnvilMenu) (Object) this).getBukkitView(), itemStack);
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
    private int roastmc$modifyMaxRepairCost(int original) {
        return maximumRepairCost;
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 39))
    private int roastmc$modifyMaxRepairCostMinusOne(int original) {
        return maximumRepairCost - 1;
    }

    @Inject(method = "createResult", at = @At("RETURN"))
    private void roastmc$createResult$return(CallbackInfo ci) {
        // CraftBukkit - SPIGOT-6686, SPIGOT-7931: Always send completed inventory to stay in sync with client
        ((AbstractContainerMenu) (Object) this).sendAllDataToRemote();
    }
}
