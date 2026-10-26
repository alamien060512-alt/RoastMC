package com.roastmc.fabric.injected.world.entity.animal;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.item.ItemStack;

public interface ContextAnimal {

    default ItemStack getBreedItem() {
        throw new ContextStateException("Not implemented");
    }
}
