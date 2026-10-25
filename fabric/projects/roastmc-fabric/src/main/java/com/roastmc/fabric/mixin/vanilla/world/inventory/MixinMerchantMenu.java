package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextMerchantMenu;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import org.bukkit.craftbukkit.inventory.view.CraftMerchantView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/5/13 17:34
 */
@Mixin(MerchantMenu.class)
public abstract class MixinMerchantMenu extends AbstractContainerMenu implements ContextMerchantMenu {

    // @formatter:off
    @Shadow @Final private Merchant trader;
    @Shadow @Final private MerchantContainer tradeContainer;
    // @formatter:on

    // CraftBukkit start
    @Unique
    private CraftMerchantView bukkitEntity = null;
    @Unique
    private Inventory player;

    protected MixinMerchantMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Override
    public CraftMerchantView getBukkitView() {
        if (bukkitEntity == null) {
            bukkitEntity = new CraftMerchantView(this.player.player.getBukkitEntity(), new org.bukkit.craftbukkit.inventory.CraftInventoryMerchant(trader, tradeContainer), (MerchantMenu) (Object) this, trader);
        }
        return bukkitEntity;
    }
    // CraftBukkit end

    @Inject(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/item/trading/Merchant;)V", at = @At("RETURN"))
    public void roastmc$init(int containerId, Inventory inventory, Merchant merchant, CallbackInfo ci) {
        this.player = inventory;
    }

    @Inject(method = "stillValid", cancellable = true, at = @At("HEAD"))
    public void roastmc$stillValid(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "playTradeSound", cancellable = true, at = @At("HEAD"))
    public void roastmc$playTradeSound(CallbackInfo ci) {
        if (!(this.trader instanceof Entity)) {
            ci.cancel();
        }
    }
}

