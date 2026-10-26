package com.roastmc.fabric.mixin.vanilla.world.level;

import com.roastmc.fabric.injected.world.level.ContextLevel;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.dimension.LevelStem;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CapturedBlockState;
import org.bukkit.entity.SpawnCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Level.class)
public abstract class MixinLevel implements ContextLevel {

    // CraftBukkit start - Added fields
    @Unique
    public org.bukkit.generator.ChunkGenerator generator;

    @Unique
    public boolean preventPoiUpdated; // SPIGOT-5710

    @Unique
    public boolean captureBlockStates;

    @Unique
    public boolean captureTreeGeneration;

    @Unique
    public Map<BlockPos, CapturedBlockState> capturedBlockStates = new java.util.LinkedHashMap<>();

    @Unique
    public Map<BlockPos, BlockEntity> capturedTileEntities = new java.util.HashMap<>();

    @Unique
    public List<ItemEntity> captureDrops;

    @Unique
    public final Object2LongOpenHashMap<SpawnCategory> ticksPerSpawnCategory = new Object2LongOpenHashMap<>();

    @Unique
    public boolean populating;

    @Unique
    public org.bukkit.generator.BiomeProvider biomeProvider;

    @Unique
    public org.bukkit.World.Environment environment;

    @Unique
    public boolean pvpMode;
    // CraftBukkit end

    @Override
    public boolean roastmc$preventPoiUpdated() {
        return preventPoiUpdated;
    }

    @Override
    public void roastmc$setPreventPoiUpdated(boolean preventPoiUpdated) {
        this.preventPoiUpdated = preventPoiUpdated;
    }

    @Override
    public org.bukkit.generator.BiomeProvider roastmc$biomeProvider() {
        return biomeProvider;
    }

    @Override
    public void roastmc$setBiomeProvider(org.bukkit.generator.BiomeProvider biomeProvider) {
        this.biomeProvider = biomeProvider;
    }

    @Override
    public org.bukkit.World.Environment roastmc$environment() {
        return environment;
    }

    @Override
    public void roastmc$setEnvironment(org.bukkit.World.Environment environment) {
        this.environment = environment;
    }

    @Override
    public org.bukkit.generator.ChunkGenerator roastmc$generator() {
        return generator;
    }

    @Override
    public void roastmc$setGenerator(org.bukkit.generator.ChunkGenerator generator) {
        this.generator = generator;
    }

    @Override
    public boolean roastmc$pvpMode() {
        return pvpMode;
    }

    @Override
    public void roastmc$setPvpMode(boolean pvpMode) {
        this.pvpMode = pvpMode;
    }

    @Override
    public boolean roastmc$captureBlockStates() {
        return captureBlockStates;
    }

    @Override
    public void roastmc$captureBlockStates(boolean captureBlockStates) {
        this.captureBlockStates = captureBlockStates;
    }

    @Override
    public boolean roastmc$captureTreeGeneration() {
        return captureTreeGeneration;
    }

    @Override
    public void roastmc$captureTreeGeneration(boolean captureTreeGeneration) {
        this.captureTreeGeneration = captureTreeGeneration;
    }

    @Override
    public Map<BlockPos, CapturedBlockState> roastmc$capturedBlockStates() {
        return capturedBlockStates;
    }

    @Override
    public void roastmc$setCapturedBlockStates(Map<BlockPos, CapturedBlockState> capturedBlockStates) {
        this.capturedBlockStates = capturedBlockStates;
    }

    @Override
    public Map<BlockPos, BlockEntity> roastmc$capturedTileEntities() {
        return capturedTileEntities;
    }

    @Override
    public void roastmc$setCapturedTileEntities(Map<BlockPos, BlockEntity> capturedTileEntities) {
        this.capturedTileEntities = capturedTileEntities;
    }

    @Override
    public List<ItemEntity> roastmc$captureDrops() {
        return captureDrops;
    }

    @Override
    public void roastmc$setCaptureDrops(List<ItemEntity> captureDrops) {
        this.captureDrops = captureDrops;
    }

    @Override
    public Object2LongOpenHashMap<SpawnCategory> roastmc$ticksPerSpawnCategory() {
        return ticksPerSpawnCategory;
    }

    @Override
    public boolean roastmc$populating() {
        return populating;
    }

    @Override
    public void roastmc$setPopulating(boolean populating) {
        this.populating = populating;
    }

    @Override
    public CraftWorld getWorld() {
        return null; // Overridden in subclasses via nms-patch
    }

    @Override
    public CraftServer getCraftServer() {
        return (CraftServer) Bukkit.getServer();
    }

    @Override
    public ResourceKey<LevelStem> getTypeKey() {
        return null; // Overridden in MixinServerLevel
    }
}
