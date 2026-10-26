package com.roastmc.fabric.injected.world.item.crafting;

import com.roastmc.fabric.util.ContextStateException;
import java.util.List;
import net.minecraft.world.item.ItemStack;

public interface ContextIngredient {

    default boolean isExact() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$itemStacks(List<ItemStack> itemStacks) {
        throw new ContextStateException("Not implemented");
    }

    default List<ItemStack> itemStacks() {
        throw new ContextStateException("Not implemented");
    }
}
