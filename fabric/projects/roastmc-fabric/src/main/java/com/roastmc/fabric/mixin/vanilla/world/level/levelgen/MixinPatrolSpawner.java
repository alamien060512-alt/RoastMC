package com.roastmc.fabric.mixin.vanilla.world.level.levelgen;

import net.minecraft.world.level.levelgen.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(PatrolSpawner.class)
public class MixinPatrolSpawner {

    @Inject(method = "tick", at = @At("HEAD"))
    private void roastmc_tick(CallbackInfo ci) {
        // CraftBukkit
    }
}
