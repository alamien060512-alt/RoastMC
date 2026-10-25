package com.roastmc.neoforge.bukkit.block;

import net.minecraft.world.level.block.entity.BlockEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.block.CraftBlockEntityState;

public class RoastMCModsBlockEntityState extends CraftBlockEntityState<BlockEntity> {

    public RoastMCModsBlockEntityState(World world, BlockEntity blockEntity, boolean useSnapshot) {
        super(world, blockEntity, useSnapshot);
    }

    protected RoastMCModsBlockEntityState(RoastMCModsBlockEntityState state, Location location) {
        super(state, location);
    }

    @Override
    public RoastMCModsBlockEntityState copy() {
        return new RoastMCModsBlockEntityState(this, null);
    }

    @Override
    public RoastMCModsBlockEntityState copy(Location location) {
        return new RoastMCModsBlockEntityState(this, location);
    }
}
