package com.roastmc.fabric.mixin.vanilla.world.level.block;

import com.roastmc.fabric.injected.world.level.block.ContextFireBlock;
import net.minecraft.world.level.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(FireBlock.class)
public class MixinFireBlock implements ContextFireBlock {
}
