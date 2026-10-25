package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextLocationContainer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.StonecutterMenu;
import org.bukkit.craftbukkit.inventory.CraftInventoryStonecutter;
import org.bukkit.craftbukkit.inventory.view.CraftStonecutterView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StonecutterMenu.class)
public abstract class MixinStonecutterMenu extends AbstractContainerMenu {

    @Shadow @Final public Container container;
    @Shadow @Final private ResultContainer resultContainer;
    @Shadow @Final private ContainerLevelAccess access;

    // CraftBukkit start
    @Unique
    private CraftStonecutterView bukkitEntity = null;

    @Unique
    private org.bukkit.entity.Player player;

    protected MixinStonecutterMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Override
    public CraftStonecutterView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryStonecutter inventory = new CraftInventoryStonecutter(this.container, this.resultContainer);
        bukkitEntity = new CraftStonecutterView(this.player, inventory, (StonecutterMenu) (Object) this);
        return bukkitEntity;
    }
    // CraftBukkit end

    @Inject(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V", at = @At("RETURN"))
    public void roastmc$init(int containerId, Inventory inventory, ContainerLevelAccess access, CallbackInfo ci) {
        this.player = (org.bukkit.entity.Player) inventory.player.getBukkitEntity();
        // CraftBukkit - set location on anonymous container
        ((ContextLocationContainer) this.container).roastmc$setLocation(access.getLocation());
    }

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void roastmc$stillValid(net.minecraft.world.entity.player.Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }
}
