package com.roastmc.fabric.injected.world.entity.monster.cubemob;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextSlime {

    default boolean canWander() {
        throw new ContextStateException("Not implemented");
    }

    default void setWander(boolean canWander) {
        throw new ContextStateException("Not implemented");
    }
}
