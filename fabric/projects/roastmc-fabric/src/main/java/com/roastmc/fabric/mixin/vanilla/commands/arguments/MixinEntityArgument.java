package com.roastmc.fabric.mixin.vanilla.commands.arguments;

import com.roastmc.fabric.injected.commands.arguments.ContextEntityArgument;
import net.minecraft.commands.arguments.EntityArgument;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(EntityArgument.class)
public class MixinEntityArgument implements ContextEntityArgument {
}
