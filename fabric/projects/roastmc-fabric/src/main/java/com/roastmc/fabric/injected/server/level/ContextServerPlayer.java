package com.roastmc.fabric.injected.server.level;

import com.roastmc.fabric.injected.world.entity.player.ContextPlayer;
import com.roastmc.fabric.util.ContextStateException;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.WeatherType;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jspecify.annotations.Nullable;

public interface ContextServerPlayer extends ContextPlayer {

    @Override
    default CraftPlayer getBukkitEntity() {
        throw new ContextStateException("Not implemented");
    }

    default int nextContainerCounterInt() {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$initialized() {
        throw new ContextStateException("Not implemented");
    }

    default String roastmc$locale() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setLocale(String locale) {
        throw new ContextStateException("Not implemented");
    }

    default long roastmc$timeOffset() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$timeOffset(long timeOffset) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$relativeTime() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$relativeTime(boolean relativeTime) {
        throw new ContextStateException("Not implemented");
    }

    default Component roastmc$listName() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$listName(Component listName) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$listOrder() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$listOrder(int listOrder) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.Location roastmc$compassTarget() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$compassTarget(org.bukkit.Location compassTarget) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$newExp(){
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$newExp(int newExp) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$newLevel() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$newLevel(int newLevel) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$newTotalExp() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$newTotalExp(int newTotalExp) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$keepLevel() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$keepLevel(boolean keepLevel) {
        throw new ContextStateException("Not implemented");
    }

    default double roastmc$maxHealthCache() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$maxHealthCache(double maxHealthCache) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$joining() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setJoining(boolean joining) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$sentListPacket() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setSentListPacket(boolean sentListPacket) {
        throw new ContextStateException("Not implemented");
    }

    default Integer roastmc$clientViewDistance() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setClientViewDistance(Integer clientViewDistance) {
        throw new ContextStateException("Not implemented");
    }

    default String roastmc$kickLeaveMessage() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setKickLeaveMessage(String kickLeaveMessage) {
        throw new ContextStateException("Not implemented");
    }

    default BlockPos getSpawnPoint(ServerLevel worldserver) {
        throw new ContextStateException("Not implemented");
    }

    default String roastmc$displayName() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$displayName(String displayName) {
        throw new ContextStateException("Not implemented");
    }

    default void spawnIn(Level world, boolean flag) {
        throw new ContextStateException("Not implemented");
    }

    default Entity changeDimension(ServerLevel worldserver, PlayerTeleportEvent.TeleportCause cause) {
        throw new ContextStateException("Not implemented");
    }

    default Either<Player.BedSleepingProblem, Unit> getBedResult(BlockPos blockposition, Direction enumdirection) {
        throw new ContextStateException("Not implemented");
    }

    default void teleportTo(ServerLevel worldserver, double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void setRespawnPosition(ServerPlayer.@Nullable RespawnConfig respawnConfig, boolean showMessage, PlayerSpawnChangeEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default long getPlayerTime(long totalTicks) {
        throw new ContextStateException("Not implemented");
    }

    default WeatherType getPlayerWeather() {
        throw new ContextStateException("Not implemented");
    }

    default void setPlayerWeather(WeatherType type, boolean plugin) {
        throw new ContextStateException("Not implemented");
    }

    default void updateWeather(float oldRain, float newRain, float oldThunder, float newThunder) {
        throw new ContextStateException("Not implemented");
    }

    default void tickWeather() {
        throw new ContextStateException("Not implemented");
    }

    default void resetPlayerWeather() {
        throw new ContextStateException("Not implemented");
    }

    default void forceSetPositionRotation(double x, double y, double z, float yaw, float pitch) {
        throw new ContextStateException("Not implemented");
    }

    default void reset() {
        throw new ContextStateException("Not implemented");
    }

    default void pushChangeDimensionCause(PlayerTeleportEvent.TeleportCause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void pushChangeSpawnCause(PlayerSpawnChangeEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$transferCookieConnection(CraftPlayer.TransferCookieConnection transferCookieConnection) {
        throw new ContextStateException("Not implemented");
    }

    default CraftPlayer.TransferCookieConnection roastmc$transferCookieConnection() {
        throw new ContextStateException("Not implemented");
    }

    default ItemEntity dropItem(boolean dropAll) {
        throw new ContextStateException("Not implemented");
    }
}
