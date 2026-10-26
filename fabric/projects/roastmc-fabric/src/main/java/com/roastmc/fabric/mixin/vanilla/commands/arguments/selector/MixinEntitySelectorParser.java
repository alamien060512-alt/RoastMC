package com.roastmc.fabric.mixin.vanilla.commands.arguments.selector;

import com.roastmc.fabric.injected.commands.arguments.selector.ContextEntitySelectorParser;
import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(EntitySelectorParser.class)
public class MixinEntitySelectorParser implements ContextEntitySelectorParser {
}
