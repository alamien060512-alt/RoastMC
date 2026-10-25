package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.EyeblossomBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EyeblossomBlock.class)
public class MixinEyeblossomBlock {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"), cancellable = true)
    private void roastmc$tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfoReturnable<Boolean> cir) {
        if (!CraftEventFactory.handleBlockFormEvent(level, pos, ((EyeblossomBlock) (Object) this).type.transform().state(), 3)) {
            cir.setReturnValue(false);
        }
    }
}
