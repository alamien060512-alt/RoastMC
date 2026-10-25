package com.roastmc.fabric.mixin.vanilla.world;

import com.roastmc.fabric.injected.world.ContextContainer;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Container.class)
public interface MixinContainer extends ContextContainer {
}
