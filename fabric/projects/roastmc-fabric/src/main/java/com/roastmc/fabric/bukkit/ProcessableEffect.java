package com.roastmc.fabric.bukkit;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.bukkit.event.entity.EntityPotionEffectEvent;

/**
 * @author Mgazul
 * @date 2026/3/28 03:38
 */
public class ProcessableEffect {
    private Holder<MobEffect> type;
    private MobEffectInstance effect;
    private final EntityPotionEffectEvent.Cause cause;

    private ProcessableEffect(MobEffectInstance effect, EntityPotionEffectEvent.Cause cause) {
        this.effect = effect;
        this.cause = cause;
    }

    private ProcessableEffect(Holder<MobEffect> type, EntityPotionEffectEvent.Cause cause) {
        this.type = type;
        this.cause = cause;
    }
}
