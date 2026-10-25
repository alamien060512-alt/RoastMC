package com.roastmc.fabric.mixin.vanilla.world.entity.animal.equine;

import com.roastmc.fabric.injected.world.entity.animal.equine.ContextLlama;
import net.minecraft.world.entity.animal.equine.Llama;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/5/1 18:24
 */
@Mixin(Llama.class)
public class MixinLlama implements ContextLlama {

    @Shadow
    private void setStrength(int strength) {

    }

    // CraftBukkit start
    @Override
    public void setStrengthPublic(int i) {
        this.setStrength(i);
    }
    // CraftBukkit end
}
