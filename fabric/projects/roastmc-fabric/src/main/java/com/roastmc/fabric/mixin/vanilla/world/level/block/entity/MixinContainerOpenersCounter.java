package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.roastmc.fabric.injected.world.level.block.entity.ContextContainerOpenersCounter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/3/28 18:43
 */
@Mixin(ContainerOpenersCounter.class)
public abstract class MixinContainerOpenersCounter implements ContextContainerOpenersCounter {

    // @formatter:off
    @Shadow protected abstract void onOpen(Level level, BlockPos pos, BlockState blockState);
    @Shadow protected abstract void onClose(Level level, BlockPos pos, BlockState blockState);
    @Shadow protected abstract void openerCountChanged(Level level, BlockPos pos, BlockState blockState, int previous, int current);
    @Unique public boolean opened; // CraftBukkit
    // @formatter:on

    @Override
    public boolean roastmc_opened() {
        return opened;
    }

    @Override
    public void roastmc_opened(boolean opened) {
        this.opened = opened;
    }

    // CraftBukkit start
    @Override
    public void onAPIOpen(Level level, BlockPos blockpos, BlockState blockstate) {
        onOpen(level, blockpos, blockstate);
    }

    @Override
    public void onAPIClose(Level level, BlockPos blockpos, BlockState blockstate) {
        onClose(level, blockpos, blockstate);
    }

    @Override
    public void openerAPICountChanged(Level level, BlockPos blockpos, BlockState blockstate, int i, int j) {
        openerCountChanged(level, blockpos, blockstate, i, j);
    }
    // CraftBukkit end
}
