package com.roastmc.fabric.injected.world.level.block;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.core.BlockPos;

public interface ContextBaseFireBlock {

    default void fireExtinguished(net.minecraft.world.level.LevelAccessor world, BlockPos position) {
        throw new ContextStateException("Not implemented");
    }
}
