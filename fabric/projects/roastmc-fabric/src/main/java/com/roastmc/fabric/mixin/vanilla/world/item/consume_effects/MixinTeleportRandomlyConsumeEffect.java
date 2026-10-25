package com.roastmc.fabric.mixin.vanilla.world.item.consume_effects;

import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(TeleportRandomlyConsumeEffect.class)
public class MixinTeleportRandomlyConsumeEffect {

    @Inject(method = "apply", at = @At("HEAD"))
    private void roastmc_apply(CallbackInfo ci) {
        // CraftBukkit
    }
}
