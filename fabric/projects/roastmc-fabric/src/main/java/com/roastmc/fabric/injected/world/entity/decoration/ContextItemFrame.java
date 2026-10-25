package com.roastmc.fabric.injected.world.entity.decoration;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.item.ItemStack;

public interface ContextItemFrame {

    default void setItem(ItemStack itemstack, boolean updateNeighbours, boolean playSound) {
        throw new ContextStateException("Not implemented");
    }
}
