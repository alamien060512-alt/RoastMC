package com.roastmc.fabric.injected.world.level.saveddata.maps;

import com.roastmc.fabric.util.ContextStateException;
import java.util.UUID;
import net.minecraft.world.level.saveddata.maps.MapId;
import org.bukkit.craftbukkit.map.CraftMapView;

public interface ContextMapItemSavedData {

    default CraftMapView roastmc_mapView() {
        throw new ContextStateException("Not implemented");
    }

    default UUID roastmc_uniqueId() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_uniqueId(UUID uuid) {
        throw new ContextStateException("Not implemented");
    }

    default MapId roastmc_id() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_id(MapId id) {
        throw new ContextStateException("Not implemented");
    }
}
