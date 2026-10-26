package com.roastmc.fabric.mixin.vanilla.network.syncher;

import com.roastmc.fabric.injected.network.syncher.ContextSynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(SynchedEntityData.class)
public class MixinSynchedEntityData implements ContextSynchedEntityData {
}
