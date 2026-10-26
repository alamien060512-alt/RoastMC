package com.roastmc.fabric.mixin.vanilla.world.entity.player;

import com.roastmc.fabric.injected.world.entity.player.ContextInventory;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Inventory.class)
public abstract class MixinInventory implements ContextInventory {

    // @formatter:off
    @Shadow public Player player;
    @Shadow public NonNullList<ItemStack> items;
    // @formatter:on

    // CraftBukkit start - add fields and methods
    public List<HumanEntity> transaction = new java.util.ArrayList<>();
    private int maxStack = Inventory.MAX_STACK;

    @Override
    public List<ItemStack> getContents() {
        List<ItemStack> combined = new ArrayList<>(Inventory.SLOT_OFFHAND + 1);
        for (int i = 0; i <= Inventory.SLOT_OFFHAND; i++) {
            combined.add(this.items.get(i));
        }
        return combined;
    }

    public List<ItemStack> getArmorContents() {
        List<ItemStack> combined = new ArrayList<>(Inventory.SLOT_OFFHAND - Inventory.INVENTORY_SIZE);
        for (int i = Inventory.INVENTORY_SIZE; i < Inventory.SLOT_OFFHAND; i++) {
            combined.add(this.items.get(i));
        }
        return combined;
    }

    @Override
    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }

    @Override
    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return transaction;
    }

    @Override
    public org.bukkit.inventory.InventoryHolder getOwner() {
        return this.player.getBukkitEntity();
    }

    public int getMaxStackSize() {
        return maxStack;
    }

    @Override
    public void setMaxStackSize(int size) {
        maxStack = size;
    }

    public Location getLocation() {
        return player.getBukkitEntity().getLocation();
    }

    // Watch method above! :D
    @Override
    public int canHold(ItemStack itemstack) {
        int remains = itemstack.getCount();
        for (int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack1 = this.items.get(i);
            if (itemstack1.isEmpty()) return itemstack.getCount();

            if (hasRemainingSpaceForItem(itemstack1, itemstack)) {
                remains -= (itemstack1.getMaxStackSize() < this.getMaxStackSize() ? itemstack1.getMaxStackSize() : this.getMaxStackSize()) - itemstack1.getCount();
            }
            if (remains <= 0) return itemstack.getCount();
        }
        ItemStack offhandItemStack = this.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.OFFHAND);
        if (hasRemainingSpaceForItem(offhandItemStack, itemstack)) {
            remains -= (offhandItemStack.getMaxStackSize() < this.getMaxStackSize() ? offhandItemStack.getMaxStackSize() : this.getMaxStackSize()) - offhandItemStack.getCount();
        }
        if (remains <= 0) return itemstack.getCount();

        return itemstack.getCount() - remains;
    }

    @Shadow
    private boolean hasRemainingSpaceForItem(ItemStack slotItemStack, ItemStack newItemStack) { return false; }
    // CraftBukkit end
}
