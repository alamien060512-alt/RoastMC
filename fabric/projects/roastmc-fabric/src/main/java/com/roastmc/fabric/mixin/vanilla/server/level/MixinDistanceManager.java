package com.roastmc.fabric.mixin.vanilla.server.level;

import net.minecraft.server.level.DistanceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(DistanceManager.class)
public class MixinDistanceManager {

    @Inject(method = "runUpdates", at = @At("HEAD"))
    private void roastmc_runUpdates(CallbackInfo ci) {
        // CraftBukkit - SPIGOT-7780: Call chunk unload events before updateHighestAllowedStatus
    }
}
