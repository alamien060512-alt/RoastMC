package com.roastmc.fabric.mixin.vanilla.world.entity.monster;

import com.roastmc.fabric.injected.world.entity.monster.cubemob.ContextSlime;
import net.minecraft.world.entity.monster.cubemob.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Slime.class)
public class MixinSlime implements ContextSlime {

    @Unique
    public boolean canWander;

    @Override
    public boolean canWander() {
        return canWander;
    }

    @Override
    public void setWander(boolean canWander) {
        this.canWander = canWander;
    }
}
