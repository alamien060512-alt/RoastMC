package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextNeutralMob;
import net.minecraft.world.entity.NeutralMob;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(NeutralMob.class)
public interface MixinNeutralMob extends ContextNeutralMob {
}
