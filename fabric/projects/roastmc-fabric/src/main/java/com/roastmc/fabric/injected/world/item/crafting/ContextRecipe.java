package com.roastmc.fabric.injected.world.item.crafting;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;

public interface ContextRecipe {

    default Recipe toBukkitRecipe(NamespacedKey id) {
        throw new ContextStateException("Not implemented");
    }
}
