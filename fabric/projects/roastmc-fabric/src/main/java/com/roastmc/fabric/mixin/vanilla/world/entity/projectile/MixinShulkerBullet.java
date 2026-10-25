package com.roastmc.fabric.mixin.vanilla.world.entity.projectile;

import com.roastmc.fabric.injected.world.entity.projectile.ContextShulkerBullet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ShulkerBullet.class)
public abstract class MixinShulkerBullet extends Projectile implements ContextShulkerBullet {

    @Shadow
    private EntityReference<Entity> finalTarget;

    public MixinShulkerBullet(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    // CraftBukkit start
    @Override
    public Entity getTarget() {
        return EntityReference.get(this.finalTarget, this.level(), Entity.class);
    }

    @Override
    public void setTarget(Entity e) {
        this.finalTarget = (e != null) ? EntityReference.of(e) : null;
    }
    // CraftBukkit end
}
