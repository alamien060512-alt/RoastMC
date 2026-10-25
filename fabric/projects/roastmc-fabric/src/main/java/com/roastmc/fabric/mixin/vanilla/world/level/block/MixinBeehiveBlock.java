package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.BeehiveBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveBlock.class)
public class MixinBeehiveBlock {

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void roastmc_setTarget(Mob mob, LivingEntity target) {
        mob.setTarget(target, org.bukkit.event.entity.EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true);
    }
}
