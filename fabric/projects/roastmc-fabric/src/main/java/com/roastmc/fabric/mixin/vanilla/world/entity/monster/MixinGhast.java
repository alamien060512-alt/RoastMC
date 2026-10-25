package com.roastmc.fabric.mixin.vanilla.world.entity.monster;

import com.roastmc.fabric.injected.world.entity.projectile.hurtingprojectile.ContextAbstractHurtingProjectile;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Ghast.class)
public class MixinGhast {

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/hurtingprojectile/LargeFireball;setPos(DDD)V"))
    private void roastmc$aiStep(LargeFireball instance, double x, double y, double z, Ghast ghast, Vec3 vec3) {
        float power = ghast.getExplosionPower();
        instance.explosionPower = (int) power;
        ((ContextAbstractHurtingProjectile) instance).roastmc$bukkitYield(power);
        instance.setPos(x, y, z);
    }
}
