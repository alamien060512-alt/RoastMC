package com.roastmc.fabric.mixin.vanilla.world.entity.decoration;

import com.roastmc.fabric.injected.world.entity.decoration.ContextArmorStand;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ArmorStand.class)
public class MixinArmorStand implements ContextArmorStand {

    @Unique
    public boolean canMove = true;

    @Override
    public boolean roastmc$canMove() {
        return canMove;
    }

    @Override
    public void roastmc$setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
