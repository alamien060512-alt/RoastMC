package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.roastmc.fabric.injected.world.level.block.entity.ContextFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(FurnaceBlockEntity.class)
public class MixinFurnaceBlockEntity implements ContextFurnaceBlockEntity {
}
