package com.roastmc.fabric.mixin.vanilla.world.item.crafting;

import com.roastmc.fabric.injected.world.item.crafting.ContextRecipeMap;
import net.minecraft.world.item.crafting.RecipeMap;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(RecipeMap.class)
public class MixinRecipeMap implements ContextRecipeMap {
}
