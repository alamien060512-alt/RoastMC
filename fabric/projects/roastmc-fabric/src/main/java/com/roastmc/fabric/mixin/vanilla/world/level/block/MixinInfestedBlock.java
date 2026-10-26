package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InfestedBlock.class)
public class MixinInfestedBlock {

    @Redirect(method = "spawnAfterBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean roastmc$spawnAfterBreak(ServerLevel instance, Silverfish silverfish, BlockState state, ServerLevel level, BlockPos pos) {
        return level.addFreshEntity(silverfish, SpawnReason.SILVERFISH_BLOCK);
    }
}
