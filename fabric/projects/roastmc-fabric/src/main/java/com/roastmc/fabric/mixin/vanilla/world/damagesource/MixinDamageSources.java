package com.roastmc.fabric.mixin.vanilla.world.damagesource;

import com.roastmc.fabric.injected.world.damagesource.ContextDamageSources;
import net.minecraft.world.damagesource.DamageSources;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(DamageSources.class)
public class MixinDamageSources implements ContextDamageSources {
}
