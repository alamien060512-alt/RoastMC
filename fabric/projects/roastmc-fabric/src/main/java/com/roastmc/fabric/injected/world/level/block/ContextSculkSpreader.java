package com.roastmc.fabric.injected.world.level.block;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.level.Level;

public interface ContextSculkSpreader {

    default Level roastmc$level() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setLevel(Level level) {
        throw new ContextStateException("Not implemented");
    }
}
