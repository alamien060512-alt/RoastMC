package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public class MixinIceBlock {

    @Inject(method = "melt", at = @At("HEAD"), cancellable = true)
    private void roastmc$melt(BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
        if (CraftEventFactory.callBlockFadeEvent(level, pos, ((IceBlock) (Object) this).meltsInto()).isCancelled()) {
            ci.cancel();
        }
    }
}
