package com.roastmc.fabric.mixin.vanilla.world.entity.animal.parrot;

import net.minecraft.world.entity.animal.parrot.ShoulderRidingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ShoulderRidingEntity.class)
public class MixinShoulderRidingEntity {

    @Redirect(method = "setEntityOnShoulder", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/parrot/ShoulderRidingEntity;discard()V"))
    private void roastmc_discard(ShoulderRidingEntity entity) {
        entity.discard(org.bukkit.event.entity.EntityRemoveEvent.Cause.PICKUP);
    }
}
