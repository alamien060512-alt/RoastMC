package com.roastmc.fabric.injected.world.level.block.entity;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextShulkerBoxBlockEntity {

    default boolean roastmc_opened() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_opened(boolean opened) {
        throw new ContextStateException("Not implemented");
    }
}
