package com.roastmc.fabric.mixin.vanilla.world.level;

import com.roastmc.fabric.injected.world.level.ContextLevelWriter;
import com.roastmc.fabric.injected.world.level.ContextServerLevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ServerLevelAccessor;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerLevelAccessor.class)
public interface MixinServerLevelAccessor extends ContextServerLevelAccessor {

    /**
     * @author
     * @reason
     */
    @Overwrite
    default void addFreshEntityWithPassengers(Entity entity) {
        this.addFreshEntityWithPassengers(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
    }

    @Override
    default void addFreshEntityWithPassengers(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        entity.getSelfAndPassengers().forEach((e) -> ((ContextLevelWriter) this).addFreshEntity(e, reason));
    }

    @Unique
    default ServerLevel getMinecraftWorld() {
        return ((ServerLevelAccessor) this).getLevel();
    }
}
