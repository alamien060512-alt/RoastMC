package com.roastmc.fabric.mixin.vanilla.world.entity.monster.illager;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.illager.Illusioner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Illusioner.class)
public class MixinIllusioner {

    @Redirect(method = "performSpellCasting", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/illager/Illusioner;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
    private boolean roastmc_addEffect(Illusioner illusioner, MobEffectInstance effect) {
        return illusioner.addEffect(effect, org.bukkit.event.entity.EntityPotionEffectEvent.Cause.ILLUSION);
    }

    @Redirect(method = "performSpellCasting", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean roastmc_addEffectToTarget(LivingEntity target, MobEffectInstance effect, LivingEntity source) {
        return target.addEffect(effect, source, org.bukkit.event.entity.EntityPotionEffectEvent.Cause.ATTACK);
    }
}
