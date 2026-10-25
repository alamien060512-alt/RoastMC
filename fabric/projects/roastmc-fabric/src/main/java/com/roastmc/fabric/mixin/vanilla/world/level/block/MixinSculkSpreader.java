package com.roastmc.fabric.mixin.vanilla.world.level.block;

import com.roastmc.fabric.injected.world.level.block.ContextSculkSpreader;
import net.minecraft.world.level.block.SculkSpreader;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(SculkSpreader.class)
public class MixinSculkSpreader implements ContextSculkSpreader {
}
