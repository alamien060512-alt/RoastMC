package com.roastmc.fabric.mixin.vanilla.world.inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.util.CraftLocation;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(PlayerEnderChestContainer.class)
public class MixinPlayerEnderChestContainer {

    // @formatter:off
    @Shadow private EnderChestBlockEntity activeChest;
    // @formatter:on

    // CraftBukkit start
    @Unique
    private Player owner;

    public InventoryHolder getBukkitOwner() {
        return owner.getBukkitEntity();
    }

    public Location getLocation() {
        return this.activeChest != null ? CraftLocation.toBukkit(this.activeChest.getBlockPos(), this.activeChest.getLevel().getWorld()) : null;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void roastmc$init(Player owner, CallbackInfo ci) {
        this.owner = owner;
    }
    // CraftBukkit end
}
