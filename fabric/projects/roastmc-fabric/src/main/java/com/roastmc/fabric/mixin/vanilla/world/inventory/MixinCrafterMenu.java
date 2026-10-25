package com.roastmc.fabric.mixin.vanilla.world.inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CrafterMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import org.bukkit.craftbukkit.inventory.CraftInventoryCrafter;
import org.bukkit.craftbukkit.inventory.view.CraftCrafterView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrafterMenu.class)
public abstract class MixinCrafterMenu extends AbstractContainerMenu {

    @Shadow @Final private Player player;
    @Shadow @Final private CraftingContainer container;
    @Shadow @Final private ResultContainer resultContainer;
    @Shadow abstract void refreshRecipeResult();

    // CraftBukkit start
    @Unique
    private CraftCrafterView bukkitEntity = null;

    protected MixinCrafterMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Override
    public CraftCrafterView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryCrafter inventory = new CraftInventoryCrafter(this.container, this.resultContainer);
        bukkitEntity = new CraftCrafterView(this.player.getBukkitEntity(), inventory, (CrafterMenu) (Object) this);
        return bukkitEntity;
    }

    @Override
    public void startOpen() {
        super.startOpen();
        this.refreshRecipeResult();
    }
    // CraftBukkit end

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void roastmc$stillValid(net.minecraft.world.entity.player.Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "refreshRecipeResult", at = @At("HEAD"), cancellable = true)
    private void roastmc$refreshRecipeResult(CallbackInfo ci) {
        if (!this.opened()) {
            ci.cancel();
        }
    }

    @Redirect(method = "refreshRecipeResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private void roastmc$refreshRecipeResult$setItem(ResultContainer resultContainer, int slot, net.minecraft.world.item.ItemStack itemstack) {
        // CraftBukkit - callPreCraftEvent
        itemstack = org.bukkit.craftbukkit.event.CraftEventFactory.callPreCraftEvent(this.container, this.resultContainer, itemstack, this.getBukkitView(), java.util.Optional.empty());
        resultContainer.setItem(slot, itemstack);
    }
}
