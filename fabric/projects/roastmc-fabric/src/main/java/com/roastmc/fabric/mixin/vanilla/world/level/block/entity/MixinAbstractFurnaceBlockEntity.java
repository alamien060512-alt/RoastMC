package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.roastmc.fabric.injected.world.level.block.entity.ContextAbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(AbstractFurnaceBlockEntity.class)
public class MixinAbstractFurnaceBlockEntity implements ContextAbstractFurnaceBlockEntity {
}
