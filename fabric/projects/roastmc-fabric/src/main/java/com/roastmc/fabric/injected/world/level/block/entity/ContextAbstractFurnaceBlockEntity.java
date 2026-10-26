package com.roastmc.fabric.injected.world.level.block.entity;

import com.roastmc.fabric.util.ContextStateException;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.phys.Vec3;

public interface ContextAbstractFurnaceBlockEntity {

    default Object2IntOpenHashMap<Identifier> getRecipesUsed() {
        throw new ContextStateException("Not implemented");
    }

    default List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel world, Vec3 vec, BlockPos pos, Player entity, ItemStack itemStack, int amount) {
        throw new ContextStateException("Not implemented");
    }

    default List<RecipeHolder<?>> roastmc$dropExp(ServerPlayer entity, ItemStack itemStack, int amount) {
        throw new ContextStateException("Not implemented");
    }
}
