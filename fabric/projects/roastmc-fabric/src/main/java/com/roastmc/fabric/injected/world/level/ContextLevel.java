package com.roastmc.fabric.injected.world.level;

import com.roastmc.fabric.util.ContextStateException;
import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.dimension.LevelStem;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CapturedBlockState;
import org.bukkit.entity.SpawnCategory;

public interface ContextLevel {

    default boolean roastmc$preventPoiUpdated() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setPreventPoiUpdated(boolean preventPoiUpdated) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.generator.BiomeProvider roastmc$biomeProvider() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setBiomeProvider(org.bukkit.generator.BiomeProvider biomeProvider) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.World.Environment roastmc$environment() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setEnvironment(org.bukkit.World.Environment environment) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.generator.ChunkGenerator roastmc$generator() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setGenerator(org.bukkit.generator.ChunkGenerator generator) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$pvpMode() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setPvpMode(boolean pvpMode) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$captureBlockStates() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$captureBlockStates(boolean captureState) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$captureTreeGeneration() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$captureTreeGeneration(boolean treeGeneration) {
        throw new ContextStateException("Not implemented");
    }

    default Map<BlockPos, CapturedBlockState> roastmc$capturedBlockStates() {
        throw new ContextStateException("Not implemented");
    }

    default Map<BlockPos, BlockEntity> roastmc$capturedTileEntities() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setCapturedTileEntities(Map<BlockPos, BlockEntity> tileEntities) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setCapturedBlockStates(Map<BlockPos, CapturedBlockState> capturedBlockStates) {
        throw new ContextStateException("Not implemented");
    }

    default List<ItemEntity> roastmc$captureDrops() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setCaptureDrops(List<ItemEntity> captureDrops) {
        throw new ContextStateException("Not implemented");
    }

    default it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap<SpawnCategory> roastmc$ticksPerSpawnCategory() {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$populating() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setPopulating(boolean populating) {
        throw new ContextStateException("Not implemented");
    }

    default ResourceKey<LevelStem> getTypeKey(){
        throw new ContextStateException("Not implemented");
    }

    default CraftWorld getWorld() {
        throw new ContextStateException("Not implemented");
    }

    default CraftServer getCraftServer() {
        throw new ContextStateException("Not implemented");
    }

    default void notifyAndUpdatePhysics(BlockPos blockpos, LevelChunk levelchunk, BlockState oldBlock, BlockState newBlock, BlockState actualBlock, int i, int j) {
        throw new ContextStateException("Not implemented");
    }

    default BlockEntity getBlockEntity(BlockPos pos, boolean validate) {
        throw new ContextStateException("Not implemented");
    }
}
