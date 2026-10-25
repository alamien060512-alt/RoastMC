package com.roastmc.fabric.injected.world.item.crafting;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.item.ItemStackTemplate;

public interface ContextSingleItemRecipe {

    default ItemStackTemplate roastmc$result() {
        throw new ContextStateException("Not implemented");
    }
}
