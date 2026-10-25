package com.roastmc.fabric.injected.world.level.gamerules;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleMap;
import net.minecraft.world.level.gamerules.GameRules;
import org.jetbrains.annotations.Nullable;

/**
 * @author Mgazul
 * @date 2026/5/13 22:09
 */
public interface ContextGameRules {

    default <T> void set(GameRule<T> gameRule, T value, @Nullable ServerLevel server) { // CraftBukkit - per-world
        throw new IllegalStateException("Not implemented");
    }

    default void setAll(GameRules other, @Nullable ServerLevel server) { // CraftBukkit - per-world
        throw new IllegalStateException("Not implemented");
    }

    default void setAll(GameRuleMap gameRulesMap, @Nullable ServerLevel server) { // CraftBukkit - per-world
        throw new IllegalStateException("Not implemented");
    }

    default <T> void setFromOther(GameRuleMap gameRulesMap, GameRule<T> gameRule, @Nullable ServerLevel server) { // CraftBukkit - per-world
        throw new IllegalStateException("Not implemented");
    }
}
