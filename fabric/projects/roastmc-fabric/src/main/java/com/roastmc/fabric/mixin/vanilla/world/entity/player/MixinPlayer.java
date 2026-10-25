package com.roastmc.fabric.mixin.vanilla.world.entity.player;

import com.roastmc.fabric.injected.world.entity.player.ContextPlayer;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Player.class)
public abstract class MixinPlayer implements ContextPlayer {

    // CraftBukkit start
    @Unique
    public boolean fauxSleeping;

    @Unique
    public int oldLevel = -1;
    // CraftBukkit end

    @Override
    public boolean roastmc$affectsSpawning() {
        return true;
    }

    @Override
    public void roastmc$setAffectsSpawning(boolean affectsSpawning) {
    }

    @Override
    public boolean roastmc$fauxSleeping() {
        return fauxSleeping;
    }

    @Override
    public void roastmc$fauxSleeping(boolean fauxSleeping) {
        this.fauxSleeping = fauxSleeping;
    }

    @Override
    public int roastmc$oldLevel() {
        return oldLevel;
    }

    @Override
    public void roastmc$setOldLevel(int oldLevel) {
        this.oldLevel = oldLevel;
    }
}
