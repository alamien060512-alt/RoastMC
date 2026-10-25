package com.roastmc.fabric.injected.world.level.block.entity;

import com.roastmc.fabric.util.ContextStateException;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;

public interface ContextFurnaceBlockEntity {

    default List<ItemStack> getContents() {
        throw new ContextStateException("Not implemented");
    }

    default void onOpen(CraftHumanEntity who) {
        throw new ContextStateException("Not implemented");
    }

    default void onClose(CraftHumanEntity who) {
        throw new ContextStateException("Not implemented");
    }

    default Object2IntOpenHashMap<Identifier> getRecipesUsed() {
        throw new ContextStateException("Not implemented");
    }

    default List<HumanEntity> getViewers() {
        throw new ContextStateException("Not implemented");
    }

    default void setMaxStackSize(int size) {
        throw new ContextStateException("Not implemented");
    }

    default void awardUsedRecipesAndPopExperience(ServerPlayer entityplayer, ItemStack itemstack, int amount) { // CraftBukkit
        throw new ContextStateException("Not implemented");
    }

    default List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel worldserver, Vec3 vec3d, BlockPos blockposition, ServerPlayer entityplayer, ItemStack itemstack, int amount) {
        throw new ContextStateException("Not implemented");
    }
}
