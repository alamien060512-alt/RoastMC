package com.roastmc.fabric.mixin.vanilla.world.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntitySelector.class)
public class MixinEntitySelector {

    @Redirect(method = "getCollisionPredicate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isPushable()Z"))
    private static boolean roastmc_canCollideWith(Entity entity1, Entity entity) {
        return entity1.canCollideWithBukkit(entity) && entity.canCollideWithBukkit(entity1);
    }
}
