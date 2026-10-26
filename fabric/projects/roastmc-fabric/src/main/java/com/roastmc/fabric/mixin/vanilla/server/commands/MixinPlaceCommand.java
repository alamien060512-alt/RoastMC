package com.roastmc.fabric.mixin.vanilla.server.commands;

import net.minecraft.server.commands.PlaceCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(PlaceCommand.class)
public class MixinPlaceCommand {

    @Inject(method = "placeStructure", at = @At("HEAD"))
    private void roastmc_placeStructure(CallbackInfo ci) {
        // CraftBukkit - set AsyncStructureGenerateEvent.Cause.COMMAND as generation cause
    }
}
