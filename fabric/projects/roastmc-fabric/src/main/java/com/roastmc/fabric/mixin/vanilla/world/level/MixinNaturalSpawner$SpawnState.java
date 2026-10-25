package com.roastmc.fabric.mixin.vanilla.world.level;

import com.roastmc.fabric.injected.world.level.ContextSpawnState;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(NaturalSpawner.SpawnState.class)
public class MixinNaturalSpawner$SpawnState implements ContextSpawnState {
}
