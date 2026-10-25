package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextConversionType;
import net.minecraft.world.entity.ConversionType;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ConversionType.class)
public class MixinConversionType implements ContextConversionType {
}
