package com.roastmc.fabric.mixin.vanilla.network;

import net.minecraft.network.FriendlyByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(FriendlyByteBuf.class)
public class MixinFriendlyByteBuf {

    @Inject(method = "writeIntIdList", at = @At("HEAD"))
    private void roastmc_writeIntIdList(CallbackInfo ci) {
        // CraftBukkit - decompile error
    }
}
