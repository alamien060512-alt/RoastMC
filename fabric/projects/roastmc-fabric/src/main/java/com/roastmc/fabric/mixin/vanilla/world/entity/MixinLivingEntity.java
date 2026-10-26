package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextLivingEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.attribute.CraftAttributeMap;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/4/2 17:00
 */
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity implements ContextLivingEntity {

    // @formatter:off
    @Shadow @Final private AttributeMap attributes;
    @Shadow @Nullable public abstract AttributeInstance getAttribute(Holder<Attribute> holder);
    // @formatter:on


    // CraftBukkit start
    public int expToDrop;
    public ArrayList<ItemStack> drops = new ArrayList<org.bukkit.inventory.ItemStack>();
    public org.bukkit.craftbukkit.attribute.CraftAttributeMap craftAttributes;
    public boolean collides = true;
    public Set<UUID> collidableExemptions = new HashSet<>();
    public boolean bukkitPickUpLoot;
    public int invulnerableDuration = 20;

    public MixinLivingEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void roastmc_initBukkit(EntityType<?> entityType, Level level, CallbackInfo ci) {
        this.collides = true;
        this.craftAttributes = new CraftAttributeMap(this.attributes);
        // CraftBukkit - setHealth(getMaxHealth()) inlined and simplified to skip the instanceof check for EntityPlayer, as getBukkitEntity() is not initialized in constructor
        this.entityData.set(LivingEntity.DATA_HEALTH_ID, (float) this.getAttribute(Attributes.MAX_HEALTH).getValue());
    }

    @Override
    public float getBukkitYaw() {
        return getYHeadRot();
    }
    // CraftBukkit end

    @Override
    public CraftAttributeMap roastmc_craftAttributes() {
        return craftAttributes;
    }

    @Override
    public void roastmc_craftAttributes(CraftAttributeMap craftAttributes) {
        this.craftAttributes = craftAttributes;
    }

    @Override
    public boolean roastmc_collides() {
        return collides;
    }

    @Override
    public void roastmc_collides(boolean collides) {
        this.collides = collides;
    }

    @Override
    public Set<UUID> roastmc_collidableExemptions() {
        return collidableExemptions;
    }

    @Override
    public void roastmc_collidableExemptions(Set<UUID> collidableExemptions) {
        this.collidableExemptions = collidableExemptions;
    }

    @Override
    public boolean roastmc_bukkitPickUpLoot() {
        return bukkitPickUpLoot;
    }

    @Override
    public void roastmc_bukkitPickUpLoot(boolean bukkitPickUpLoot) {
        this.bukkitPickUpLoot = bukkitPickUpLoot;
    }

    @Override
    public int invulnerableDuration() {
       return invulnerableDuration;
    }

    @Override
    public void invulnerableDuration(int duration) {
        invulnerableDuration = duration;
    }
}
