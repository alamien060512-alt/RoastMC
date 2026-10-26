package com.roastmc.fabric.mixin.vanilla.world.level.block.entity.trialspawner;

import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerStateData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(TrialSpawnerStateData.class)
public class MixinTrialSpawnerStateData {

    @Inject(method = "tick", at = @At("HEAD"))
    private void roastmc_tick(CallbackInfo ci) {
        // CraftBukkit
    }
}
