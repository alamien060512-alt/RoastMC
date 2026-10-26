package com.roastmc.fabric.mixin.vanilla.world.entity.projectile;

import com.roastmc.fabric.injected.world.entity.projectile.ContextProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Projectile.class)
public abstract class MixinProjectile implements ContextProjectile {

    @Accessor("hitCancelled")
    @Override
    public abstract boolean hitCancelled();

    @Accessor("hitCancelled")
    @Override
    public abstract void roastmc$setHitCancelled(boolean cancelled);
}
