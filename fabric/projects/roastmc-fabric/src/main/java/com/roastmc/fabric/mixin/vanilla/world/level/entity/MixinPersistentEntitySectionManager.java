package com.roastmc.fabric.mixin.vanilla.world.level.entity;

import com.roastmc.fabric.injected.world.level.entity.ContextPersistentEntitySectionManager;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(PersistentEntitySectionManager.class)
public class MixinPersistentEntitySectionManager implements ContextPersistentEntitySectionManager {
}
