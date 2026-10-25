package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextLecternMenu;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.LecternMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.craftbukkit.inventory.CraftInventoryLectern;
import org.bukkit.craftbukkit.inventory.view.CraftLecternView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/5/13 21:30
 */
@Mixin(LecternMenu.class)
public abstract class MixinLecternMenu extends AbstractContainerMenu implements ContextLecternMenu {

    @Shadow @Final private Container lectern;

    protected MixinLecternMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    // CraftBukkit start
    private CraftLecternView bukkitEntity = null;
    private org.bukkit.entity.Player player;

    @Override
    public CraftLecternView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryLectern inventory = new CraftInventoryLectern(this.lectern);
        bukkitEntity = new CraftLecternView(this.player, inventory, (LecternMenu) (Object) this);
        return bukkitEntity;
    }
    // CraftBukkit end

    @Override
    public LecternMenu roastmc$player(Inventory playerInventory) {
        player = (org.bukkit.entity.Player) playerInventory.player.getBukkitEntity(); // CraftBukkit
        return (LecternMenu) (Object) this;
    }

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void roastmc$stillValid(net.minecraft.world.entity.player.Player player, CallbackInfoReturnable<Boolean> cir) {
        // CraftBukkit - checkReachable
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "clickMenuButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/Container;removeItemNoUpdate(I)Lnet/minecraft/world/item/ItemStack;", shift = At.Shift.BEFORE), cancellable = true)
    private void roastmc$clickMenuButton(net.minecraft.world.entity.player.Player player, int buttonId, CallbackInfoReturnable<Boolean> cir) {
        PlayerTakeLecternBookEvent event = new PlayerTakeLecternBookEvent(this.player, ((CraftInventoryLectern) getBukkitView().getTopInventory()).getHolder());
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue(false);
        }
    }
}
