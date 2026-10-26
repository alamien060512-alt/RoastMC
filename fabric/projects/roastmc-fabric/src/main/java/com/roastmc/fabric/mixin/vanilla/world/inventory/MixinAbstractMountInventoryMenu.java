package com.roastmc.fabric.mixin.vanilla.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractMountInventoryMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.inventory.InventoryView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(AbstractMountInventoryMenu.class)
public abstract class MixinAbstractMountInventoryMenu extends AbstractContainerMenu{

    // @formatter:off
    @Shadow protected Container mountContainer;
    // @formatter:on

    // CraftBukkit start
    @Unique
    CraftInventoryView bukkitEntity;

    @Unique
    Inventory player;

    protected MixinAbstractMountInventoryMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    public InventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }
        return bukkitEntity = new CraftInventoryView(player.player.getBukkitEntity(), mountContainer.getOwner().getInventory(), (AbstractContainerMenu) (Object) this);
    }
    // CraftBukkit end

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void roastmc$stillValid(net.minecraft.world.entity.player.Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }
}
