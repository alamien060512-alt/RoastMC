package com.roastmc.fabric.mixin.vanilla.world.level;

import com.roastmc.fabric.injected.world.level.ContextExplosion;
import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Explosion.class)
public class MixinExplosion implements ContextExplosion {

    @Unique
    public float yield;

    @Unique
    public boolean wasCanceled;

    @Override
    public float roastmc$getYield() {
        return yield;
    }

    @Override
    public void roastmc$setYield(float yield) {
        this.yield = yield;
    }

    @Override
    public boolean roastmc$wasCanceled() {
        return wasCanceled;
    }

    @Override
    public void roastmc$wasCanceled(boolean wasCanceled) {
        this.wasCanceled = wasCanceled;
    }
}
