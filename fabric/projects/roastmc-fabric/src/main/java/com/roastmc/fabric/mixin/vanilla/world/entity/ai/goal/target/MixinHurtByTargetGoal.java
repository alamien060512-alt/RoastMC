package com.roastmc.fabric.mixin.vanilla.world.entity.ai.goal.target;

import com.roastmc.fabric.injected.world.entity.ContextMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/10/24
 */
@Mixin(HurtByTargetGoal.class)
public class MixinHurtByTargetGoal {

    // CraftBukkit start
    @Redirect(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void roastmc$targetAttackedEntity(Mob mob, LivingEntity target) {
        ((ContextMob) mob).setTarget(target, EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY, true);
    }

    @Redirect(method = "alertOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void roastmc$targetAttackedNearbyEntity(Mob other, LivingEntity hurtByMob) {
        ((ContextMob) other).setTarget(hurtByMob, EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, true);
    }
    // CraftBukkit end
}
