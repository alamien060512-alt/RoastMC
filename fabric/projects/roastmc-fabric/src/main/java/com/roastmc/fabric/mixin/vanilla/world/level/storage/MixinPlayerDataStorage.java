package com.roastmc.fabric.mixin.vanilla.world.level.storage;

import com.roastmc.fabric.injected.world.level.storage.ContextPlayerDataStorage;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(PlayerDataStorage.class)
public class MixinPlayerDataStorage implements ContextPlayerDataStorage {
}
