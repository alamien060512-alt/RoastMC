package com.roastmc.fabric.mixin.vanilla.world.level.storage;

import com.roastmc.fabric.injected.world.level.storage.ContextDerivedLevelData;
import net.minecraft.world.level.storage.DerivedLevelData;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(DerivedLevelData.class)
public class MixinDerivedLevelData implements ContextDerivedLevelData {
}
