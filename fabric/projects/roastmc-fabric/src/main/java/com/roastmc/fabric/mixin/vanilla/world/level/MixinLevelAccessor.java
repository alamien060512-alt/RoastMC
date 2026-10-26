package com.roastmc.fabric.mixin.vanilla.world.level;

import com.roastmc.fabric.injected.world.level.ContextLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(LevelAccessor.class)
public interface MixinLevelAccessor extends ContextLevelAccessor {
}
