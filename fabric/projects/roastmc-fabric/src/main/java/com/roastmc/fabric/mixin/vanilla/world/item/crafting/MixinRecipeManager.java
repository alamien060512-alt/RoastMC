package com.roastmc.fabric.mixin.vanilla.world.item.crafting;

import com.roastmc.fabric.bukkit.BukkitUtils;
import com.roastmc.fabric.injected.server.players.ContextPlayerList;
import com.roastmc.fabric.injected.world.item.crafting.ContextRecipeMap;
import com.roastmc.fabric.injected.world.item.crafting.ContextRecipeManager;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(RecipeManager.class)
public abstract class MixinRecipeManager implements ContextRecipeManager {

    @Mutable
    @Shadow
    @Final
    private RecipeMap recipes;

    @Shadow
    public abstract void finalizeRecipeLoading(FeatureFlagSet enabledFlags);

    @Unique
    private FeatureFlagSet roastmc$featureflagset;

    @Unique
    private HolderLookup.RegistryLookup<Recipe<?>> roastmc$lookup;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void roastmc$captureLookup(HolderLookup.Provider registries, CallbackInfo ci) {
        this.roastmc$lookup = registries.lookupOrThrow(Registries.RECIPE); // CraftBukkit
    }

    // CraftBukkit start
    @Override
    public void addRecipe(RecipeHolder<?> irecipe) {
        ((ContextRecipeMap) this.recipes).addRecipe(irecipe);
        this.finalizeRecipeLoading();
    }

    @Override
    public void finalizeRecipeLoading() {
        if (this.roastmc$featureflagset != null) {
            this.finalizeRecipeLoading(this.roastmc$featureflagset);

            ((ContextPlayerList) BukkitUtils.getServer().getPlayerList()).reloadRecipes();
        }
    }

    @Inject(method = "finalizeRecipeLoading(Lnet/minecraft/world/flag/FeatureFlagSet;)V", at = @At("HEAD"))
    private void roastmc$captureFlags(FeatureFlagSet enabledFlags, CallbackInfo ci) {
        this.roastmc$featureflagset = enabledFlags;
    }

    @Override
    public boolean removeRecipe(ResourceKey<Recipe<?>> mcKey) {
        boolean removed = ((ContextRecipeMap) this.recipes).removeRecipe(mcKey);
        if (removed) {
            this.finalizeRecipeLoading();
        }

        return removed;
    }

    @Override
    public void clearRecipes() {
        this.recipes = RecipeMap.create(this.roastmc$lookup);
        this.finalizeRecipeLoading();
    }
    // CraftBukkit end

    /**
     * @author Mgazul
     * @reason CraftBukkit - SPIGOT-4638: last recipe gets priority
     */
    @Overwrite
    public <I extends RecipeInput, T extends Recipe<I>> Optional<RecipeHolder<T>> getRecipeFor(RecipeType<T> type, I input, Level level) {
        List<RecipeHolder<T>> list = this.recipes.getRecipesFor(type, input, level).toList();
        return (list.isEmpty()) ? Optional.empty() : Optional.of(list.getLast()); // CraftBukkit - SPIGOT-4638
    }
}
