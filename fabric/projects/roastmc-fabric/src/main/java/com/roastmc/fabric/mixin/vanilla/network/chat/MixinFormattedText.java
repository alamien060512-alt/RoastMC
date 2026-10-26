package com.roastmc.fabric.mixin.vanilla.network.chat;

import net.minecraft.network.chat.FormattedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(FormattedText.class)
public interface MixinFormattedText {

    @Inject(method = "getString", at = @At("HEAD"))
    private void roastmc_getString(CallbackInfoReturnable<String> cir) {
        // CraftBukkit - Component string exceeded maximum length check
    }
}
