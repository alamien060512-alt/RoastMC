package com.roastmc.fabric.mixin.vanilla.world.level.levelgen.structure.templatesystem;

import com.roastmc.fabric.injected.world.level.levelgen.structure.templatesystem.ContextStructureTemplateManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(StructureTemplateManager.class)
public class MixinStructureTemplateManager implements ContextStructureTemplateManager {
}
