package com.roastmc.fabric.injected.world.entity.animal.allay;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.animal.allay.Allay;

public interface ContextAllay {

    default Allay duplicateAllay0() {
        throw new ContextStateException("Not implemented");
    }

    default void setCanDuplicate(boolean canDuplicate) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$forceDancing() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$forceDancing(boolean forceDancing) {
        throw new ContextStateException("Not implemented");
    }
}
