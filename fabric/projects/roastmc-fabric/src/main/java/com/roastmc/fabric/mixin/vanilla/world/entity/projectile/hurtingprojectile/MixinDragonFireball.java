package com.roastmc.fabric.mixin.vanilla.world.entity.projectile.hurtingprojectile;

import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(DragonFireball.class)
public class MixinDragonFireball {

    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/hurtingprojectile/DragonFireball;discard()V"))
    private void roastmc_discard(DragonFireball fireball) {
        fireball.discard(org.bukkit.event.entity.EntityRemoveEvent.Cause.HIT); // CraftBukkit - add Bukkit remove cause
    }
}
