package com.roastmc.fabric.mixin.vanilla.world.level;

import com.roastmc.fabric.injected.world.level.ContextLevelWriter;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(WorldGenRegion.class)
public abstract class MixinWorldGenRegion implements ContextLevelWriter {

    @Override
    public boolean addFreshEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        return ((WorldGenRegion) (Object) this).addFreshEntity(entity);
    }
}
