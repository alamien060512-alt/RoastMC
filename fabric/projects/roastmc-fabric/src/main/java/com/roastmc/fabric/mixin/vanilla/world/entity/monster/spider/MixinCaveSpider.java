package com.roastmc.fabric.mixin.vanilla.world.entity.monster.spider;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CaveSpider.class)
public class MixinCaveSpider {

    @Redirect(method = "doHurtTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean roastmc_addEffect(LivingEntity target, MobEffectInstance effect, Entity source) {
        return target.addEffect(effect, source, org.bukkit.event.entity.EntityPotionEffectEvent.Cause.ATTACK);
    }
}
