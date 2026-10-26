package com.roastmc.fabric.mixin.vanilla.world.entity.monster;

import com.roastmc.fabric.injected.world.entity.monster.ContextGuardian;
import net.minecraft.world.entity.monster.Guardian;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/5/1 17:20
 */
@Mixin(Guardian.class)
public class MixinGuardian implements ContextGuardian {

    public Guardian.GuardianAttackGoal guardianAttackGoal; // CraftBukkit - add field

    public Guardian.GuardianAttackGoal roastmc$guardianAttackGoal() {
       return guardianAttackGoal;
    }

    public void roastmc$guardianAttackGoal(Guardian.GuardianAttackGoal guardianAttackGoal) {
        this.guardianAttackGoal = guardianAttackGoal;
    }
}
