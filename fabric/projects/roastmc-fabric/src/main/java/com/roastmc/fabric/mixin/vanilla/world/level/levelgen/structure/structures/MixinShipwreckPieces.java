package com.roastmc.fabric.mixin.vanilla.world.level.levelgen.structure.structures;

import net.minecraft.world.level.levelgen.structure.structures.ShipwreckPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ShipwreckPieces.class)
public class MixinShipwreckPieces {

    @Inject(method = "addPiece", at = @At("HEAD"))
    private void roastmc_addPiece(CallbackInfo ci) {
        // CraftBukkit
    }
}
