package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScaffoldingBlock.class)
public class MixinScaffoldingBlock {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/FallingBlockEntity;fall(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/FallingBlockEntity;"), cancellable = true)
    private void roastmc$tick(BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
        if (CraftEventFactory.callBlockFadeEvent(level, pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState()).isCancelled()) {
            ci.cancel();
        }
    }
}
