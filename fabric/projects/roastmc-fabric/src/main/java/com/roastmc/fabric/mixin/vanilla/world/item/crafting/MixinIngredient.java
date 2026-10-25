package com.roastmc.fabric.mixin.vanilla.world.item.crafting;

import com.roastmc.fabric.injected.world.item.crafting.ContextIngredient;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/5/13 21:35
 */
@Mixin(Ingredient.class)
public class MixinIngredient implements ContextIngredient {

    @Unique
    @Nullable
    private List<ItemStack> itemStacks;

    @Override
    public boolean isExact() {
        return this.itemStacks != null;
    }

    @Override
    public void roastmc$itemStacks(List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }

    @Override
    public List<ItemStack> itemStacks() {
        return this.itemStacks;
    }
}
