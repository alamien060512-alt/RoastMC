package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextLightningBolt;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(LightningBolt.class)
public class MixinLightningBolt implements ContextLightningBolt {

    @Unique
    public boolean isSilent;

    @Override
    public boolean roastmc$isSilent() {
        return isSilent;
    }

    @Override
    public void roastmc$setIsSilent(boolean isSilent) {
        this.isSilent = isSilent;
    }
}
