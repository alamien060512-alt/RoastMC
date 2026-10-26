package com.roastmc.fabric.mixin.vanilla.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryDoubleChest;
import org.bukkit.craftbukkit.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestMenu.class)
public abstract class MixinChestMenu extends AbstractContainerMenu {

    @Shadow @Final private Container container;

    // CraftBukkit start
    @Unique
    private CraftInventoryView bukkitEntity = null;

    @Unique
    private Inventory player;

    protected MixinChestMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventory inventory;
        if (this.container instanceof Inventory) {
            inventory = new CraftInventoryPlayer((Inventory) this.container);
        } else if (this.container instanceof CompoundContainer) {
            inventory = new CraftInventoryDoubleChest((CompoundContainer) this.container);
        } else {
            inventory = new CraftInventory(this.container);
        }

        bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, (ChestMenu) (Object) this);
        return bukkitEntity;
    }

    @Override
    public void startOpen() {
        super.startOpen();
        this.container.startOpen(this.player.player);
    }
    // CraftBukkit end

    @Inject(method = "<init>(Lnet/minecraft/world/inventory/MenuType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;I)V", at = @At("RETURN"))
    public void roastmc$init(MenuType<?> menuType, int containerId, Inventory inventory, Container container, int rows, CallbackInfo ci) {
        this.player = inventory;
    }

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void roastmc$stillValid(net.minecraft.world.entity.player.Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }
}
