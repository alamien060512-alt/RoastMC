package com.roastmc.fabric.injected.world.entity.monster;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.monster.Guardian;

public interface ContextGuardian {

    default Guardian.GuardianAttackGoal roastmc$guardianAttackGoal() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$guardianAttackGoal(Guardian.GuardianAttackGoal guardianAttackGoal) {
        throw new ContextStateException("Not implemented");
    }
}
