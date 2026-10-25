package com.roastmc.fabric.injected.world.food;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

public interface ContextFoodData {

    default int roastmc$saturatedRegenRate() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$saturatedRegenRate(int saturatedRegenRate) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$unsaturatedRegenRate() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$unsaturatedRegenRate(int unsaturatedRegenRate) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$starvationRate() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$starvationRate(int starvationRate) {
        throw new ContextStateException("Not implemented");
    }

    default void eat(FoodProperties foodproperties, ItemStack itemstack, ServerPlayer serverplayer) {
        throw new ContextStateException("Not implemented");
    }
}
