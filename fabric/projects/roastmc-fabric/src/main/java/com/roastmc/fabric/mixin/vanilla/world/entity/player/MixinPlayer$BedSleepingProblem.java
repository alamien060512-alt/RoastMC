package com.roastmc.fabric.mixin.vanilla.world.entity.player;

import com.roastmc.fabric.injected.world.entity.player.ContextPlayerBedSleepingProblem;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Player.BedSleepingProblem.class)
public class MixinPlayer$BedSleepingProblem implements ContextPlayerBedSleepingProblem {
}
