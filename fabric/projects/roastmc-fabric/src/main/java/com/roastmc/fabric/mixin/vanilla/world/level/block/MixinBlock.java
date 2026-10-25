package com.roastmc.fabric.mixin.vanilla.world.level.block;

import com.roastmc.fabric.injected.world.level.block.ContextBlock;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Block.class)
public class MixinBlock implements ContextBlock {
}
