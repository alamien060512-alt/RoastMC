package com.roastmc.fabric.mixin.vanilla.world.level.portal;

import com.roastmc.fabric.injected.world.level.portal.ContextPortalForcer;
import net.minecraft.world.level.portal.PortalForcer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(PortalForcer.class)
public class MixinPortalForcer implements ContextPortalForcer {

    // CraftBukkit start
    @Unique
    public int portalCreateRadius;

    @Unique
    public int searchRadius;
    // CraftBukkit end

    @Override
    public void pushPortalCreate(net.minecraft.world.entity.Entity entity, int createRadius) {
        this.portalCreateRadius = createRadius;
    }

    @Override
    public void pushSearchRadius(int searchRadius) {
        this.searchRadius = searchRadius;
    }
}
