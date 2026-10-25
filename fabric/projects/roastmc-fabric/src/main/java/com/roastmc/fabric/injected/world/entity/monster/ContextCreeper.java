package com.roastmc.fabric.injected.world.entity.monster;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.Entity;

public interface ContextCreeper {

    default void setPowered(boolean power) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$entityIgniter(Entity entityIgniter) {
        throw new ContextStateException("Not implemented");
    }

    default Entity roastmc$entityIgniter() {
        throw new ContextStateException("Not implemented");
    }
}
