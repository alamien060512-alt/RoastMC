package com.roastmc.fabric.mixin.vanilla.world.level.block;

import com.roastmc.fabric.injected.world.level.block.ContextBaseFireBlock;
import net.minecraft.world.level.block.BaseFireBlock;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(BaseFireBlock.class)
public class MixinBaseFireBlock implements ContextBaseFireBlock {
}
