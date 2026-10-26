package com.roastmc.fabric.injected.world;

import com.roastmc.fabric.util.ContextStateException;
import java.util.Collections;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;

public interface ContextContainer {

    int MAX_STACK = 99;

    default java.util.List<ItemStack> getContents() {
        return Collections.emptyList();
    }

    default void onOpen(CraftHumanEntity who) {
        throw new ContextStateException("Not implemented");
    }

    default void onClose(CraftHumanEntity who) {
        throw new ContextStateException("Not implemented");
    }

    default java.util.List<org.bukkit.entity.HumanEntity> getViewers() {
        return Collections.emptyList();
    }

    default org.bukkit.inventory.InventoryHolder getOwner() {
        throw new ContextStateException("Not implemented");
    }

    default void setOwner(org.bukkit.inventory.InventoryHolder owner) {
        throw new ContextStateException("Not implemented");
    }

    default void setMaxStackSize(int size) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.Location getLocation() {
        throw new ContextStateException("Not implemented");
    }
}
