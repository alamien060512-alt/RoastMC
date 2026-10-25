package com.roastmc.fabric.mixin.vanilla.world.item.component;

import net.minecraft.world.item.component.SuspiciousStewEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(SuspiciousStewEffects.class)
public class MixinSuspiciousStewEffects {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void roastmc_init(CallbackInfo ci) {
        // CraftBukkit
    }
}
