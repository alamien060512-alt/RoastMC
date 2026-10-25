package com.roastmc.fabric.mixin.vanilla.util.datafix.fixes;

import net.minecraft.util.datafix.fixes.ItemStackTheFlatteningFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ItemStackTheFlatteningFix.class)
public class MixinItemStackTheFlatteningFix {

    @Inject(method = "fixItemStack", at = @At("HEAD"))
    private void roastmc_fixItemStack(CallbackInfo ci) {
        // CraftBukkit
    }
}
