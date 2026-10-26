package com.roastmc.fabric.injected.world.inventory;

import net.minecraft.world.item.crafting.RecipeHolder;

/**
 * @author Mgazul
 * @date 2026/5/13 21:25
 */
public interface ContextCraftingContainer {

    // CraftBukkit start
    default RecipeHolder<?> getCurrentRecipe() {
        return null;
    }

    default void setCurrentRecipe(RecipeHolder<?> recipe) {
    }
    // CraftBukkit end
}
