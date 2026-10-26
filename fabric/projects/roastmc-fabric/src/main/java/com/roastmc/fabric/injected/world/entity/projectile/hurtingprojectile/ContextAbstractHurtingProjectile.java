package com.roastmc.fabric.injected.world.entity.projectile.hurtingprojectile;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextAbstractHurtingProjectile {

    default float roastmc$bukkitYield() {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$isIncendiary() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$bukkitYield(float yield) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$isIncendiary(boolean incendiary) {
        throw new ContextStateException("Not implemented");
    }
}
