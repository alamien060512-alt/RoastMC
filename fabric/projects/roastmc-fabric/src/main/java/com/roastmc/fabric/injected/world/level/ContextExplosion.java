package com.roastmc.fabric.injected.world.level;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextExplosion {

    default float roastmc$getYield() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setYield(float yield) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$wasCanceled() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$wasCanceled(boolean wasCanceled) {
        throw new ContextStateException("Not implemented");
    }
}
