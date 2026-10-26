package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextCraftingContainer;
import net.minecraft.world.inventory.CraftingContainer;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/5/13 21:27
 */
@Mixin(CraftingContainer.class)
public interface MixinCraftingContainer extends ContextCraftingContainer {
}
