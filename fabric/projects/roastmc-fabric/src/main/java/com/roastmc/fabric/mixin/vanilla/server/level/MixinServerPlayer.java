package com.roastmc.fabric.mixin.vanilla.server.level;

import com.roastmc.fabric.injected.server.level.ContextServerPlayer;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/5/13 17:07
 */
@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player implements ContextServerPlayer {

    // @formatter:off
    @Shadow private int containerCounter;
    @Shadow public void nextContainerCounter() {}
    // @formatter:on

    // CraftBukkit start
    public CraftPlayer.TransferCookieConnection transferCookieConnection;
    public String displayName;
    public Component listName;
    public int listOrder = 0;
    public org.bukkit.Location compassTarget;
    public int newExp = 0;
    public int newLevel = 0;
    public int newTotalExp = 0;
    public boolean keepLevel = false;
    public double maxHealthCache;
    public boolean joining = true;
    public boolean sentListPacket = false;
    public String kickLeaveMessage = null; // SPIGOT-3034: Forward leave message to PlayerQuitEvent
    // CraftBukkit end
    public PlayerSpawnChangeEvent.Cause respawnPositionCause = PlayerSpawnChangeEvent.Cause.UNKNOWN;
    public boolean initialized = false;

    public MixinServerPlayer(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Override
    public int nextContainerCounterInt() {
        nextContainerCounter();
        return containerCounter;
    }

    @Override
    public void roastmc$transferCookieConnection(CraftPlayer.TransferCookieConnection transferCookieConnection) {
        this.transferCookieConnection = transferCookieConnection;
    }

    @Override
    public CraftPlayer.TransferCookieConnection roastmc$transferCookieConnection() {
        return this.transferCookieConnection;
    }

    @Override
    public String roastmc$displayName() {

        return displayName;
    }

    @Override
    public void roastmc$displayName(String displayName) {
        this.displayName = displayName;
    }
}
