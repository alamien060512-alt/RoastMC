package com.roastmc.fabric.injected.world.level.block;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextFireBlock {

    default boolean roastmc$canBurn(net.minecraft.world.level.block.Block block) {
        throw new ContextStateException("Not implemented");
    }
}
