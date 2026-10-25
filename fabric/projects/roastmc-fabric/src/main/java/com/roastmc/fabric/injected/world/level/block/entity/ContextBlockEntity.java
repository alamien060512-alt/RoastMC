package com.roastmc.fabric.injected.world.level.block.entity;

import com.roastmc.fabric.util.ContextStateException;
import java.util.Set;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.bukkit.craftbukkit.persistence.CraftPersistentDataContainer;
import org.bukkit.inventory.InventoryHolder;

public interface ContextBlockEntity {

    default CraftPersistentDataContainer roastmc_persistentDataContainer() {
        throw new ContextStateException("Not implemented");
    }

    default InventoryHolder roastmc_getOwner() {
        throw new ContextStateException("Not implemented");
    }

    default void setPatterns(BannerPatternLayers bannerPatternLayers) {
        throw new ContextStateException("Not implemented");
    }

    default Set<DataComponentType<?>> applyComponentsSet(DataComponentMap datacomponentmap, DataComponentPatch datacomponentpatch) {
        return Set.of();
    }
}
