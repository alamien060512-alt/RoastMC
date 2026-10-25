package com.roastmc.fabric.mixin.vanilla.world.entity.monster.creaking;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.creaking.Creaking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Creaking.class)
public class MixinCreaking {

    @Redirect(method = "die", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/creaking/Creaking;remove(Lnet/minecraft/world/entity/Entity$RemovalReason;)V"))
    private void roastmc_remove(Creaking creaking, Entity.RemovalReason reason) {
        creaking.remove(Entity.RemovalReason.DISCARDED, null);
    }
}
