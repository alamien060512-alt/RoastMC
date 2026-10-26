package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.roastmc.fabric.bukkit.BukkitUtils;
import io.izzel.arclight.mixin.TransformAccess;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/4/2 17:44
 */
@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity {


    @TransformAccess(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
    private static List getHumansInRange(Level level, BlockPos worldPosition, int i) {
        return BukkitUtils.getHumansInRange(level, worldPosition, i);
    }
}
