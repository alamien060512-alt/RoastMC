package com.roastmc.fabric.mixin.vanilla.world.entity.ai.goal.target;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OwnerHurtByTargetGoal.class)
public class MixinOwnerHurtByTargetGoal {

    @Redirect(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void roastmc_setTarget(Mob mob, LivingEntity target) {
        mob.setTarget(target, org.bukkit.event.entity.EntityTargetEvent.TargetReason.TARGET_ATTACKED_OWNER, true);
    }
}
