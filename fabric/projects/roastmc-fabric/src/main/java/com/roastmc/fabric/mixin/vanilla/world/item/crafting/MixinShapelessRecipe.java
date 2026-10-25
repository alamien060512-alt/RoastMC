package com.roastmc.fabric.mixin.vanilla.world.item.crafting;

import com.roastmc.fabric.injected.world.item.crafting.ContextShapelessRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ShapelessRecipe.class)
public class MixinShapelessRecipe implements ContextShapelessRecipe {
}
