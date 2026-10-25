package com.roastmc.fabric.mixin.vanilla.world.entity.raid;

import com.roastmc.fabric.injected.world.entity.raid.ContextRaid;
import net.minecraft.world.entity.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Raid.class)
public class MixinRaid implements ContextRaid {
}
