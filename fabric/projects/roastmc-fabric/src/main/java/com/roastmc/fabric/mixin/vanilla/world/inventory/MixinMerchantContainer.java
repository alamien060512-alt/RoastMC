package com.roastmc.fabric.mixin.vanilla.world.inventory;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftAbstractVillager;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(MerchantContainer.class)
public class MixinMerchantContainer {

    // @formatter:off
    @Shadow private Merchant merchant;
    @Shadow private NonNullList<ItemStack> itemStacks;
    // @formatter:on

    // CraftBukkit start - add fields and methods
    @Unique
    public List<HumanEntity> transaction = new ArrayList<>();

    @Unique
    private int maxStack = 64;

    public List<ItemStack> getContents() {
        return this.itemStacks;
    }

    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }

    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
        merchant.setTradingPlayer((Player) null);
    }

    public List<HumanEntity> getViewers() {
        return transaction;
    }

    public int getMaxStackSize() {
        return maxStack;
    }

    public void setMaxStackSize(int i) {
        maxStack = i;
    }

    public org.bukkit.inventory.InventoryHolder getOwner() {
        return (merchant instanceof LivingEntity) ? (CraftAbstractVillager) ((LivingEntity) this.merchant).getBukkitEntity() : null;
    }

    public Location getLocation() {
        return (merchant instanceof Villager) ? ((Villager) this.merchant).getBukkitEntity().getLocation() : null;
    }
    // CraftBukkit end
}
