package com.roastmc.fabric.injected.world.level.block.entity;

import com.roastmc.fabric.util.ContextStateException;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface ContextBeehiveBlockEntity {

    default int roastmc$maxBees() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$maxBees(int maxBees) {
        throw new ContextStateException("Not implemented");
    }

    default List<Entity> releaseBees(BlockState iblockdata, BeehiveBlockEntity.BeeReleaseStatus tileentitybeehive_releasestatus, boolean force) {
        throw new ContextStateException("Not implemented");
    }
}
