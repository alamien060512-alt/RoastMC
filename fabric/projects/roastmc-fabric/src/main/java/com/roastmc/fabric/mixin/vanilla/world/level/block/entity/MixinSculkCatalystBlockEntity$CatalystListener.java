package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.roastmc.fabric.injected.world.level.block.entity.ContextCatalystListener;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(SculkCatalystBlockEntity.CatalystListener.class)
public class MixinSculkCatalystBlockEntity$CatalystListener implements ContextCatalystListener {
}
