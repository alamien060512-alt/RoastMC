package com.roastmc.fabric.injected.world.item.crafting;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextRecipeHolder {

    default org.bukkit.inventory.Recipe toBukkitRecipe() {
        throw new ContextStateException("Not implemented");
    }
}
