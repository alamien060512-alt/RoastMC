package com.roastmc.fabric.injected.world.item.crafting;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public interface ContextRecipeMap {

    default void addRecipe(RecipeHolder<?> irecipe) {

    }

    default  boolean removeRecipe(ResourceKey<Recipe<?>> mcKey) {
        throw new ContextStateException("Not implemented");
    }
}
