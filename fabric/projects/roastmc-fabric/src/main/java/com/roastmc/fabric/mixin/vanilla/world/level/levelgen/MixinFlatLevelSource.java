package com.roastmc.fabric.mixin.vanilla.world.level.levelgen;

import com.roastmc.fabric.injected.world.level.levelgen.ContextFlatLevelSource;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(FlatLevelSource.class)
public class MixinFlatLevelSource implements ContextFlatLevelSource {
}
