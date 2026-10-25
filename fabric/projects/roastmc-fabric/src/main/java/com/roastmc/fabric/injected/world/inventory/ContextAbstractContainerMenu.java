package com.roastmc.fabric.injected.world.inventory;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.inventory.InventoryView;

public interface ContextAbstractContainerMenu {

    default boolean roastmc$checkReachable() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$checkReachable(boolean checkReachable) {
        throw new ContextStateException("Not implemented");
    }

    default InventoryView getBukkitView() {
        throw new ContextStateException("Not implemented");
    }

    default void setBukkitView(InventoryView view) {
        throw new ContextStateException("Not implemented");
    }

    default Player containerOwner() {
        throw new ContextStateException("Not implemented");
    }

    default void containerOwner(Player containerOwner) {
        throw new ContextStateException("Not implemented");
    }

    default InventoryView getBukkitView(AbstractContainerMenu other){
        throw new ContextStateException("Not implemented");
    }

    default void transferTo(AbstractContainerMenu other, CraftHumanEntity player) {
        throw new ContextStateException("Not implemented");
    }

    default Component getTitle() {
        throw new ContextStateException("Not implemented");
    }

    default void setTitle(Component title) {
        throw new ContextStateException("Not implemented");
    }

    default void broadcastCarriedItem() {
        throw new ContextStateException("Not implemented");
    }

    default void startOpen() {

    }

    default boolean opened() {
        return false;
    }
}
