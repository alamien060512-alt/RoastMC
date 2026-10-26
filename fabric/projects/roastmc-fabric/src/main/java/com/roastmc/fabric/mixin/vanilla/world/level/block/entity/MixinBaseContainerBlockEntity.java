package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(BaseContainerBlockEntity.class)
public class MixinBaseContainerBlockEntity {

    @Shadow
    @Nullable
    protected Level level;

    @Shadow
    protected BlockPos worldPosition;

    public org.bukkit.Location getLocation() {
        if (level == null) return null;
        return new org.bukkit.Location(level.getWorld(), worldPosition.getX(), worldPosition.getY(), worldPosition.getZ());
    }
}
