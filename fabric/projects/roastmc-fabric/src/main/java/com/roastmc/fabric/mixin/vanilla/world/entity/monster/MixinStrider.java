package com.roastmc.fabric.mixin.vanilla.world.entity.monster;

import net.minecraft.world.entity.monster.Strider;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Strider.class)
public class MixinStrider {

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Strider;setSuffocating(Z)V"))
    private void roastmc$aiStep(Strider instance, boolean suffocating) {
        if (suffocating ^ instance.isSuffocating()) {
            if (CraftEventFactory.callStriderTemperatureChangeEvent(instance, suffocating)) {
                instance.setSuffocating(suffocating);
            }
        }
    }
}
