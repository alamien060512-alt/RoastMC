package com.roastmc.fabric.injected.world.level.border;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.level.Level;

public interface ContextWorldBorder {

    default Level roastmc$world() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setWorld(Level world) {
        throw new ContextStateException("Not implemented");
    }

    default void applyInitialSettings(long gameTime, boolean force) {
        throw new ContextStateException("Not implemented");
    }
}
