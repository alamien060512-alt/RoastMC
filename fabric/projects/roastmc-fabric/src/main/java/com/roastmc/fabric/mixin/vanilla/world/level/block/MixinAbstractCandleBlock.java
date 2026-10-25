package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractCandleBlock.class)
public class MixinAbstractCandleBlock {

    @Inject(method = "onProjectileHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/AbstractCandleBlock;setLit(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Z)V"), cancellable = true)
    private void roastmc$onProjectileHit(BlockState state, Level level, BlockPos pos, Projectile projectile, BlockHitResult hitResult, CallbackInfo ci) {
        if (CraftEventFactory.callBlockIgniteEvent(level, hitResult.getBlockPos(), projectile).isCancelled()) {
            ci.cancel();
        }
    }
}
