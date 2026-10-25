package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(DaylightDetectorBlock.class)
public class MixinDaylightDetectorBlock {

    @Inject(method = "useWithoutItem", at = @At("HEAD"))
    private void roastmc_useWithoutItem(CallbackInfoReturnable<InteractionResult> cir) {
        // CraftBukkit
    }
}
