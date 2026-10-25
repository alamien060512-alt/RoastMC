package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CeilingHangingSignBlock.class)
public class MixinCeilingHangingSignBlock {

    @Inject(method = "getTicker", at = @At("RETURN"), cancellable = true)
    private <T extends BlockEntity> void roastmc_nullTicker(Level level, BlockState blockState, BlockEntityType<T> type, CallbackInfoReturnable<BlockEntityTicker<T>> cir) {
        cir.setReturnValue(null);
    }
}
