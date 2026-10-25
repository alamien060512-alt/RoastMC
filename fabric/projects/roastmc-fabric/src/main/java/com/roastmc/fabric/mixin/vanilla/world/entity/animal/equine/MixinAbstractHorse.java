package com.roastmc.fabric.mixin.vanilla.world.entity.animal.equine;

import com.roastmc.fabric.injected.world.entity.animal.equine.ContextAbstractHorse;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/5/1 16:57
 */
@Mixin(AbstractHorse.class)
public class MixinAbstractHorse implements ContextAbstractHorse {

    @Unique
    public int maxDomestication = 100; // CraftBukkit - store max domestication value

    public int roastmc$maxDomestication() {
        return maxDomestication;
    }

    public void roastmc$maxDomestication(int maxDomestication) {
        this.maxDomestication = maxDomestication;
    }
}
