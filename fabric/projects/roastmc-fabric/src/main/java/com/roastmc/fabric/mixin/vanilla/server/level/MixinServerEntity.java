package com.roastmc.fabric.mixin.vanilla.server.level;

import com.roastmc.fabric.injected.server.level.ContextServerEntity;
import net.minecraft.server.level.ServerEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ServerEntity.class)
public class MixinServerEntity implements ContextServerEntity {
}
