package com.roastmc.fabric.mixin.vanilla.world.entity.projectile;

import com.roastmc.fabric.injected.world.entity.projectile.ContextProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Projectile.class)
public abstract class MixinProjectile implements ContextProjectile {

    // CraftBukkit start
    @Unique
    private boolean roastmc$hitCancelled;

    @Shadow
    protected abstract ProjectileDeflection hitTargetOrDeflectSelf(HitResult hitResult);

    @Override
    public boolean hitCancelled() {
        return roastmc$hitCancelled;
    }

    @Override
    public void roastmc$setHitCancelled(boolean cancelled) {
        roastmc$hitCancelled = cancelled;
    }

    @Override
    public ProjectileDeflection preHitTargetOrDeflectSelf(HitResult hitResult) {
        ProjectileHitEvent event = CraftEventFactory.callProjectileHitEvent((Projectile) (Object) this, hitResult);
        this.roastmc$hitCancelled = event != null && event.isCancelled();
        if (hitResult.getType() == HitResult.Type.BLOCK || !this.roastmc$hitCancelled) {
            return this.hitTargetOrDeflectSelf(hitResult);
        }
        return ProjectileDeflection.NONE;
    }

    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    private void roastmc$onHitBlock(BlockHitResult hitResult, CallbackInfo ci) {
        if (this.roastmc$hitCancelled) {
            ci.cancel();
        }
    }
    // CraftBukkit end
}
