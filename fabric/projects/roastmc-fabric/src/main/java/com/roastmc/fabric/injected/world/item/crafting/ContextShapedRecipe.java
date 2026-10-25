package com.roastmc.fabric.injected.world.item.crafting;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.NamespacedKey;

public interface ContextShapedRecipe extends ContextRecipe {

    @Override
    default org.bukkit.inventory.ShapedRecipe toBukkitRecipe(NamespacedKey id) {
        throw new ContextStateException("Not implemented");
    }

}
