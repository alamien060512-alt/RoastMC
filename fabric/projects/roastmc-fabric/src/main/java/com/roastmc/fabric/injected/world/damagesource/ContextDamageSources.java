package com.roastmc.fabric.injected.world.damagesource;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public interface ContextDamageSources {

    default DamageSource melting() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource poison() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource explosion(@Nullable Entity entity, @Nullable Entity entity1, ResourceKey<DamageType> resourceKey) {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource badRespawnPointExplosion(Vec3 vec3, org.bukkit.block.BlockState blockState) {
        throw new ContextStateException("Not implemented");
    }
}
