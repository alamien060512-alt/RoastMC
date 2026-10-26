package com.roastmc.fabric.injected.world.level.levelgen.structure.templatesystem;

import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public interface ContextStructureTemplateManager {

    default Optional<StructureTemplate> loadFromResource0(Identifier id) {
        return Optional.empty();
    }
}
