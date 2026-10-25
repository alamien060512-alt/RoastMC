package com.roastmc.fabric.mixin.vanilla.world.entity.projectile.throwableitemprojectile;

import com.roastmc.fabric.injected.world.entity.projectile.throwableitemprojectile.ContextThrowableItemProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ThrowableItemProjectile.class)
public abstract class MixinThrowableItemProjectile implements ContextThrowableItemProjectile {

    @Shadow
    protected abstract Item getDefaultItem();

    @Override
    public Item getDefaultItemPublic() {
        return getDefaultItem();
    }
}
