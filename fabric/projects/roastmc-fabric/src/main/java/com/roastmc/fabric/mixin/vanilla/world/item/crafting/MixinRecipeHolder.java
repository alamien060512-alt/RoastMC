package com.roastmc.fabric.mixin.vanilla.world.item.crafting;

import com.roastmc.fabric.injected.world.item.crafting.ContextRecipeHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(RecipeHolder.class)
public abstract class MixinRecipeHolder implements ContextRecipeHolder {

    @Shadow
    public abstract ResourceKey<Recipe<?>> id();

    @Shadow
    public abstract Recipe<?> value();

    // CraftBukkit start
    @Override
    public org.bukkit.inventory.Recipe toBukkitRecipe() {
        return value().toBukkitRecipe(CraftNamespacedKey.fromMinecraft(id().identifier()));
    }
    // CraftBukkit end
}
