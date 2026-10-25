package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/3/28 05:14
 */
@Mixin(Entity.class)
public abstract class MixinEntity implements ContextEntity {

    // @formatter:off
    @Shadow private float yRot;
    @Shadow private Level level;
    @Shadow @Final public abstract double getX();
    @Shadow @Final public abstract double getZ();
    // CraftBukkit start
    @Unique public boolean forceDrops;
    @Unique public boolean persist = true;
    @Unique public boolean visibleByDefault = true;
    @Unique public boolean valid;
    @Unique public boolean inWorld = false;
    @Unique public boolean generation;
    @Unique public int maxAirTicks = getDefaultMaxAirSupply(); // CraftBukkit - SPIGOT-6907: re-implement LivingEntity#setMaximumAir()
    @Unique public org.bukkit.projectiles.ProjectileSource projectileSource; // For projectiles only
    @Unique public boolean lastDamageCancelled; // SPIGOT-5339, SPIGOT-6252, SPIGOT-6777: Keep track if the event was canceled
    @Unique public boolean persistentInvisibility = false;
    @Unique public BlockPos lastLavaContact;
    // Marks an entity, that it was removed by a plugin via Entity#remove
    // Main use case currently is for SPIGOT-7487, preventing dropping of leash when leash is removed
    @Unique public boolean pluginRemoved = false;
    // @formatter:on

    public float getBukkitYaw() {
        return this.yRot;
    }

    public boolean isChunkLoaded() {
        return level.hasChunk((int) Math.floor(this.getX()) >> 4, (int) Math.floor(this.getZ()) >> 4);
    }
    // CraftBukkit end

    @Override
    public boolean roastmc_generation() {
        return generation;
    }

    @Override
    public void roastmc_generation(boolean gen) {
        generation = gen;
    }

    @Override
    public int roastmc_maxAirTicks() {
        return maxAirTicks;
    }

    @Override
    public void roastmc_maxAirTicks(int maxAirTicks) {
        this.maxAirTicks = maxAirTicks;
    }

    @Override
    public org.bukkit.projectiles.ProjectileSource roastmc_projectileSource() {
       return projectileSource;
    }

    @Override
    public void roastmc_projectileSource(org.bukkit.projectiles.ProjectileSource projectileSource) {
        this.projectileSource = projectileSource;
    }

    @Override
    public boolean roastmc_pluginRemoved() {
       return pluginRemoved;
    }

    @Override
    public void roastmc_pluginRemoved(boolean pluginRemoved) {
       this.pluginRemoved = pluginRemoved;
    }


    @Override
    public boolean roastmc_valid() {
       return valid;
    }

    @Override
    public void roastmc_valid(boolean valid) {
       this.valid = valid;
    }

    @Override
    public boolean roastmc_inWorld() {
       return inWorld;
    }

    @Override
    public void roastmc_inWorld(boolean inWorld) {
       this.inWorld = inWorld;
    }

    @Override
    public boolean roastmc_visibleByDefault() {
       return visibleByDefault;
    }

    @Override
    public void roastmc_visibleByDefault(boolean visibleByDefault) {
       this.visibleByDefault = visibleByDefault;
    }

    @Override
    public boolean roastmc_persist() {
       return persist;
    }

    @Override
    public void roastmc_persist(boolean persist) {
        this.persist = persist;
    }

    @Override
    public boolean roastmc_persistentInvisibility() {
       return persistentInvisibility;
    }

    @Override
    public void roastmc_persistentInvisibility(boolean persistentInvisibility) {
        this.persistentInvisibility = persistentInvisibility;
    }
}
