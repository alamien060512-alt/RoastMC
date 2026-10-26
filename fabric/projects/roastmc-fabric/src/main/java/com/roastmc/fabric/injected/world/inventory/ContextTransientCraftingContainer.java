package com.roastmc.fabric.injected.world.inventory;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.inventory.InventoryType;

public interface ContextTransientCraftingContainer {

    default InventoryType getInvType() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setResultInventory(Container resultInventory) {
        throw new ContextStateException("Not implemented");
    }


    default void setOwner(Player owner) {
        throw new ContextStateException("Not implemented");
    }
}
