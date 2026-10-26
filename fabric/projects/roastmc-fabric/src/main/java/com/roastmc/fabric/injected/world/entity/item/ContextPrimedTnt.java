package com.roastmc.fabric.injected.world.entity.item;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextPrimedTnt {

    default boolean roastmc$isIncendiary() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$isIncendiary(boolean isIncendiary) {
        throw new ContextStateException("Not implemented");
    }
}
