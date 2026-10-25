package com.roastmc.fabric.injected.world.entity.projectile.throwableitemprojectile;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.item.Item;

public interface ContextThrowableItemProjectile {

    default Item getDefaultItemPublic() {
        throw new ContextStateException("Not implemented");
    }
}
