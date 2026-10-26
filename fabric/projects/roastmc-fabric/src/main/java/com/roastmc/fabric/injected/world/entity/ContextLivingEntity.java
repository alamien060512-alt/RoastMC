package com.roastmc.fabric.injected.world.entity;

import com.roastmc.fabric.bukkit.ProcessableEffect;
import com.roastmc.fabric.util.ContextStateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.attribute.CraftAttributeMap;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.jetbrains.annotations.Nullable;

public interface ContextLivingEntity extends ContextEntity {

    default void equipEventAndSound(EquipmentSlot slot, ItemStack oldItem, ItemStack newItem, boolean silent) {
        throw new ContextStateException("Not implemented");
    }

    default Optional<EntityPotionEffectEvent.Cause> getEffectCause() {
        return Optional.empty();
    }

    default void pushHealReason(EntityRegainHealthEvent.RegainReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default void pushEffectCause(EntityPotionEffectEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void pushExhaustionCause(EntityExhaustionEvent.ExhaustionReason cause) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$expToDrop() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$expToDrop(int expToDrop) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$forceDrops() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$forceDrops(boolean forceDrops) {
        throw new ContextStateException("Not implemented");
    }

    default ArrayList<org.bukkit.inventory.ItemStack> roastmc$drops() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setDrops(ArrayList<org.bukkit.inventory.ItemStack> drops) {
        throw new ContextStateException("Not implemented");
    }

    default CraftAttributeMap roastmc_craftAttributes() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_craftAttributes(CraftAttributeMap craftAttributes) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_collides() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_collides(boolean collides) {
        throw new ContextStateException("Not implemented");
    }

    default Set<UUID> roastmc_collidableExemptions() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_collidableExemptions(Set<UUID> collidableExemptions) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_bukkitPickUpLoot() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_bukkitPickUpLoot(boolean bukkitPickUpLoot) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$isTickingEffects() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setIsTickingEffects(boolean isTickingEffects) {
        throw new ContextStateException("Not implemented");
    }

    default List<ProcessableEffect> roastmc$effectsToProcess() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setEffectsToProcess(List<ProcessableEffect> effectsToProcess) {
        throw new ContextStateException("Not implemented");
    }

    default void onEquipItem(EquipmentSlot enumitemslot, ItemStack itemstack, ItemStack itemstack1, boolean silent) {
        throw new ContextStateException("Not implemented");
    }

    default boolean removeAllEffects(EntityPotionEffectEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default boolean addEffect(MobEffectInstance mobeffect, EntityPotionEffectEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default boolean addEffect(MobEffectInstance mobeffect, @Nullable Entity entity, EntityPotionEffectEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    @Nullable
    default MobEffectInstance c(@Nullable MobEffect mobeffectlist, EntityPotionEffectEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default boolean removeEffect(Holder<MobEffect> holder, EntityPotionEffectEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void heal(float f, EntityRegainHealthEvent.RegainReason regainReason) {
        throw new ContextStateException("Not implemented");
    }

    default int getExpReward(ServerLevel handle, @Nullable Entity entity) {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getHurtSound0(DamageSource damagesource) {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getDeathSound0() {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getFallDamageSound0(int fallHeight) {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getDrinkingSound0(ItemStack itemstack) {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getEatingSound0(ItemStack itemstack) {
        throw new ContextStateException("Not implemented");
    }

    default boolean damageEntity0(final DamageSource damagesource, float f) { // void -> boolean, add final
        throw new ContextStateException("Not implemented");
    }

    default void setArrowCount(int i, boolean flag) {
        throw new ContextStateException("Not implemented");
    }

    default void setItemSlot(EquipmentSlot enumitemslot, ItemStack itemstack, boolean silent) {
        throw new ContextStateException("Not implemented");
    }

    default ItemEntity drop(ItemStack itemStack, boolean bl, boolean bl2, boolean callEvent) {
        throw new ContextStateException("Not implemented");
    }

    default MobEffectInstance removeEffectNoUpdate(Holder<MobEffect> holder, EntityPotionEffectEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default int invulnerableDuration() {
        throw new ContextStateException("Not implemented");
    }

    default void invulnerableDuration(int duration) {
        throw new ContextStateException("Not implemented");
    }
}
