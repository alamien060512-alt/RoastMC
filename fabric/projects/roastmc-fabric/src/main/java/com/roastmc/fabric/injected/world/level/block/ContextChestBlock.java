package com.roastmc.fabric.injected.world.level.block;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ContextChestBlock {

    default MenuProvider getMenuProvider(BlockState iblockdata, Level world, BlockPos blockposition, boolean ignoreObstructions) {
        throw new ContextStateException("Not implemented");
    }
}
