package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.roastmc.fabric.bukkit.BukkitUtils;
import io.izzel.arclight.mixin.TransformAccess;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/4/2 19:27
 */
@Mixin(ConduitBlockEntity.class)
public class MixinConduitBlockEntity {

    @Unique
    @TransformAccess(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
    private static int getRange(List<BlockPos> effectBlocks) {
        return BukkitUtils.getRange(effectBlocks);
    }
}
