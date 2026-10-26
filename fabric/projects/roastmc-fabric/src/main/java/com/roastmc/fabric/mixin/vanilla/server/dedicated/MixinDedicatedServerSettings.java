package com.roastmc.fabric.mixin.vanilla.server.dedicated;

import net.minecraft.server.dedicated.DedicatedServerSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(DedicatedServerSettings.class)
public class MixinDedicatedServerSettings {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void roastmc_init(CallbackInfo ci) {
        // CraftBukkit
    }
}
