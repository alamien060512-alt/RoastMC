package com.roastmc.fabric.injected.world.entity.player;

import com.roastmc.fabric.util.ContextStateException;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;

public interface ContextInventory {

    default List<ItemStack> getContents() {
        throw new ContextStateException("Not implemented");
    }

    default List<ItemStack> getArmorContents() {
        throw new ContextStateException("Not implemented");
    }

    default void onOpen(CraftHumanEntity who) {
        throw new ContextStateException("Not implemented");
    }

    default void onClose(CraftHumanEntity who) {
        throw new ContextStateException("Not implemented");
    }

    default List<HumanEntity> getViewers() {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.inventory.InventoryHolder getOwner(){
        throw new ContextStateException("Not implemented");
    }

    default void setMaxStackSize(int size) {
        throw new ContextStateException("Not implemented");
    }

    default int canHold(ItemStack itemstack) {
        throw new ContextStateException("Not implemented");
    }

}
