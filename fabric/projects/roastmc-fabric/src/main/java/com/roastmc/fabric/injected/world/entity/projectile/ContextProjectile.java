package com.roastmc.fabric.injected.world.entity.projectile;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.phys.HitResult;

public interface ContextProjectile {

    default boolean hitCancelled() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setHitCancelled(boolean cancelled) {
        throw new ContextStateException("Not implemented");
    }

    default ProjectileDeflection preHitTargetOrDeflectSelf(HitResult movingobjectposition) {
        throw new ContextStateException("Not implemented");
    }
}
