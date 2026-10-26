package com.roastmc.fabric.mixin.vanilla.world.entity.ai.village;

import com.roastmc.fabric.injected.world.entity.ai.village.ContextReputationEventType;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ReputationEventType.class)
public class MixinReputationEventType implements ContextReputationEventType {
}
