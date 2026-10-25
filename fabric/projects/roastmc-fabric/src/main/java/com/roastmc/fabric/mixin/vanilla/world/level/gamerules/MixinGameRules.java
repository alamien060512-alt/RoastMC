package com.roastmc.fabric.mixin.vanilla.world.level.gamerules;

import com.roastmc.fabric.injected.world.level.gamerules.ContextGameRules;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(GameRules.class)
public class MixinGameRules implements ContextGameRules {
}
