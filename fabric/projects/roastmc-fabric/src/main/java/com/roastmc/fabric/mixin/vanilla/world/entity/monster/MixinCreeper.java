package com.roastmc.fabric.mixin.vanilla.world.entity.monster;

import com.roastmc.fabric.injected.world.entity.monster.ContextCreeper;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/5/1 17:12
 */
@Mixin(Creeper.class)
public abstract class MixinCreeper extends Monster implements ContextCreeper {

    @Shadow @Final
    private static EntityDataAccessor<Boolean> DATA_IS_POWERED;

    @Unique
    public Entity entityIgniter; // CraftBukkit

    protected MixinCreeper(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public void setPowered(boolean power) {
        this.entityData.set(DATA_IS_POWERED, power);
    }

    public void roastmc$entityIgniter(Entity entityIgniter) {
        this.entityIgniter = entityIgniter;
    }

    public Entity roastmc$entityIgniter() {
        return entityIgniter;
    }
}
