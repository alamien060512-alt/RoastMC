package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DoublePlantBlock.class)
public class MixinDoublePlantBlock {

    @Inject(method = "preventDropFromBottomPart", at = @At("HEAD"), cancellable = true)
    private static void roastmc$preventDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player, CallbackInfo ci) {
        if (CraftEventFactory.callBlockPhysicsEvent(level, pos).isCancelled()) {
            ci.cancel();
        }
    }
}
