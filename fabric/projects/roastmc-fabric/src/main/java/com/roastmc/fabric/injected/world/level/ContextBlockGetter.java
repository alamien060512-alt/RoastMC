package com.roastmc.fabric.injected.world.level;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;

public interface ContextBlockGetter {

    default BlockHitResult clip(ClipContext raytrace1, BlockPos blockposition) {
        throw new ContextStateException("Not implemented");
    }
}
