package com.roastmc.fabric.mixin.vanilla.world.item.crafting;

import com.roastmc.fabric.injected.world.item.crafting.ContextSingleItemRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/10/24
 */
@Mixin(SingleItemRecipe.class)
public abstract class MixinSingleItemRecipe implements ContextSingleItemRecipe {

    @Shadow
    @Final
    private ItemStackTemplate result;

    // CraftBukkit start
    @Override
    public ItemStackTemplate roastmc$result() {
        return this.result;
    }
    // CraftBukkit end
}
