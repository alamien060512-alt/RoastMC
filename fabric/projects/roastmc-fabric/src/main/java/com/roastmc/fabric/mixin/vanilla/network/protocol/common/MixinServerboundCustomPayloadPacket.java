package com.roastmc.fabric.mixin.vanilla.network.protocol.common;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ServerboundCustomPayloadPacket.class)
public class MixinServerboundCustomPayloadPacket {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void roastmc_init(CallbackInfo ci) {
        // CraftBukkit - treat all packets the same
    }
}
