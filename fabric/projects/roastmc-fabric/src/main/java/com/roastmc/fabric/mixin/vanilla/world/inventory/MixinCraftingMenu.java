package com.roastmc.fabric.mixin.vanilla.world.inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.inventory.CraftInventoryCrafting;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingMenu.class)
public abstract class MixinCraftingMenu extends AbstractContainerMenu {

    @Shadow @Final public TransientCraftingContainer craftSlots;
    @Shadow @Final public ResultContainer resultSlots;
    @Shadow @Final private Player player;

    // CraftBukkit start
    @Unique
    private CraftInventoryView bukkitEntity = null;

    protected MixinCraftingMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftSlots, this.resultSlots);
        bukkitEntity = new CraftInventoryView(this.player.getBukkitEntity(), inventory, (CraftingMenu) (Object) this);
        return bukkitEntity;
    }
    // CraftBukkit end

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void roastmc$stillValid(net.minecraft.world.entity.player.Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }

    @Redirect(method = "slotChangedCraftingGrid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private static void roastmc$slotChangedCraftingGrid$setItem(ResultContainer resultSlots, int slot, ItemStack itemstack) {
        // CraftBukkit - callPreCraftEvent
        itemstack = org.bukkit.craftbukkit.event.CraftEventFactory.callPreCraftEvent(null, resultSlots, itemstack, null, java.util.Optional.empty());
        resultSlots.setItem(slot, itemstack);
    }
}
