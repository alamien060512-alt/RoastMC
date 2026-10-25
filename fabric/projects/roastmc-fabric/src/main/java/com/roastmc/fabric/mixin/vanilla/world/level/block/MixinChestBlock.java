package com.roastmc.fabric.mixin.vanilla.world.level.block;

import com.roastmc.fabric.injected.world.level.block.ContextChestBlock;
import net.minecraft.world.level.block.ChestBlock;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ChestBlock.class)
public class MixinChestBlock implements ContextChestBlock {
}
