package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextEntityType;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(EntityType.class)
public class MixinEntityType implements ContextEntityType {
}
