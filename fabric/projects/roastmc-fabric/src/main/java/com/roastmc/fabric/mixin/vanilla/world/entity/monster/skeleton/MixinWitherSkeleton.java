package com.roastmc.fabric.mixin.vanilla.world.entity.monster.skeleton;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WitherSkeleton.class)
public class MixinWitherSkeleton {

    @Redirect(method = "doHurtTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
    private boolean roastmc$doHurtTarget(LivingEntity instance, MobEffectInstance effect) {
        return instance.addEffect(effect, EntityPotionEffectEvent.Cause.ATTACK);
    }
}
