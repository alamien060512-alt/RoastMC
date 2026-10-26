package com.roastmc.fabric.mixin.vanilla.world.entity.monster.spider;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.spider.Spider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Spider.class)
public class MixinSpider {

    @Redirect(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
    private boolean roastmc_addEffect(LivingEntity entity, MobEffectInstance effect) {
        return entity.addEffect(effect, org.bukkit.event.entity.EntityPotionEffectEvent.Cause.SPIDER_SPAWN);
    }
}
