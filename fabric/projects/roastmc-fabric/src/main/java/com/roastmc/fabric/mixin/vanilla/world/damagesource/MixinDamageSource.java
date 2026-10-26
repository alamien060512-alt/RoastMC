package com.roastmc.fabric.mixin.vanilla.world.damagesource;

import com.roastmc.fabric.injected.world.damagesource.ContextDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(DamageSource.class)
public class MixinDamageSource implements ContextDamageSource {

    // @formatter:off
    @Shadow private Holder<DamageType> type;
    @Shadow private @Nullable Entity causingEntity;
    @Shadow private @Nullable Entity directEntity;
    @Shadow private @Nullable Vec3 damageSourcePosition;
    // @formatter:on

    // CraftBukkit start
    @Unique
    private org.bukkit.block.@Nullable Block directBlock;
    @Unique
    private org.bukkit.block.@Nullable BlockState directBlockState;
    @Unique
    private boolean sweep;
    @Unique
    private boolean melting;
    @Unique
    private boolean poison;
    @Unique
    private Entity customEntityDamager;
    @Unique
    private Entity customCausingEntityDamager;

    @Override
    public DamageSource sweep() {
        this.sweep = true;
        return (DamageSource) (Object) this;
    }

    @Override
    public boolean isSweep() {
        return this.sweep;
    }

    @Override
    public DamageSource melting() {
        this.melting = true;
        return (DamageSource) (Object) this;
    }

    @Override
    public boolean isMelting() {
        return this.melting;
    }

    @Override
    public DamageSource poison() {
        this.poison = true;
        return (DamageSource) (Object) this;
    }

    @Override
    public boolean isPoison() {
        return this.poison;
    }

    @Override
    public Entity getDamager() {
        return (this.customEntityDamager != null) ? this.customEntityDamager : this.directEntity;
    }

    @Override
    public Entity getCausingDamager() {
        return (this.customCausingEntityDamager != null) ? this.customCausingEntityDamager : this.causingEntity;
    }

    @Override
    public DamageSource customEntityDamager(Entity entity) {
        if (this.customEntityDamager != null || this.directEntity == entity || this.causingEntity == entity) {
            return (DamageSource) (Object) this;
        }
        DamageSource damageSource = this.cloneInstance();
        ((MixinDamageSource) (Object) damageSource).customEntityDamager = entity;
        return damageSource;
    }

    @Override
    public void roastmc$setCustomEntityDamager(Entity entity) {
        this.customEntityDamager = entity;
    }

    @Override
    public DamageSource customCausingEntityDamager(Entity entity) {
        if (this.customCausingEntityDamager != null || this.directEntity == entity || this.causingEntity == entity) {
            return (DamageSource) (Object) this;
        }
        DamageSource damageSource = this.cloneInstance();
        ((MixinDamageSource) (Object) damageSource).customCausingEntityDamager = entity;
        return damageSource;
    }

    @Override
    public void roastmc$setCustomCausingEntityDamager(Entity entity) {
        this.customCausingEntityDamager = entity;
    }

    @Override
    public Block getDirectBlock() {
        return this.directBlock;
    }

    @Override
    public DamageSource directBlock(Level level, BlockPos blockPosition) {
        if (blockPosition == null || level == null) {
            return (DamageSource) (Object) this;
        }
        return directBlock(CraftBlock.at(level, blockPosition));
    }

    @Override
    public DamageSource directBlock(Block block) {
        if (block == null) {
            return (DamageSource) (Object) this;
        }
        DamageSource damageSource = this.cloneInstance();
        ((MixinDamageSource) (Object) damageSource).directBlock = block;
        return damageSource;
    }

    @Override
    public void roastmc$setDirectBlock(Block block) {
        this.directBlock = block;
    }

    @Override
    public BlockState getDirectBlockState() {
        return this.directBlockState;
    }

    @Override
    public DamageSource directBlockState(BlockState blockState) {
        if (blockState == null) {
            return (DamageSource) (Object) this;
        }
        DamageSource damageSource = this.cloneInstance();
        ((MixinDamageSource) (Object) damageSource).directBlockState = blockState;
        return damageSource;
    }

    @Override
    public void roastmc$setDirectBlockState(BlockState blockState) {
        this.directBlockState = blockState;
    }

    @Override
    public DamageSource cloneInstance() {
        DamageSource damageSource = new DamageSource(this.type, this.directEntity, this.causingEntity, this.damageSourcePosition);
        MixinDamageSource mixin = (MixinDamageSource) (Object) damageSource;
        mixin.directBlock = this.getDirectBlock();
        mixin.directBlockState = this.getDirectBlockState();
        mixin.sweep = this.isSweep();
        mixin.poison = this.isPoison();
        mixin.melting = this.isMelting();
        return damageSource;
    }

    @Override
    public Entity getCausingEntity() {
        return this.causingEntity;
    }

    @Override
    public void roastmc$setSweep(boolean sweep) {
        this.sweep = sweep;
    }

    @Override
    public void roastmc$setPoison(boolean poison) {
        this.poison = poison;
    }

    @Override
    public void roastmc$setMelting(boolean melting) {
        this.melting = melting;
    }
    // CraftBukkit end
}
