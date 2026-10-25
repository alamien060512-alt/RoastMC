package com.roastmc.fabric.mixin.vanilla.world.waypoints;

import net.minecraft.world.waypoints.WaypointTransmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(WaypointTransmitter.class)
public class MixinWaypointTransmitter {

    @Inject(method = "tick", at = @At("HEAD"))
    private void roastmc_tick(CallbackInfo ci) {
        // CraftBukkit
    }
}
