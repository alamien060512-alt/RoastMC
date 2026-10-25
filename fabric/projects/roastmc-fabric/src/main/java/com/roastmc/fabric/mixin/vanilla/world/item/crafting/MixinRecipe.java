package com.roastmc.fabric.mixin.vanilla.world.item.crafting;

import com.roastmc.fabric.injected.world.item.crafting.ContextRecipe;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Recipe.class)
public interface MixinRecipe extends ContextRecipe {
}
