package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextMob;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/4/2 20:01
 */
@Mixin(Mob.class)
public class MixinMob implements ContextMob {

    @Unique
    public boolean aware = true; // CraftBukkit

    @Override
    public boolean roastmc_aware() {
        return aware;
    }

    @Override
    public void roastmc_aware(boolean aware){
        this.aware = aware;
    }
}
