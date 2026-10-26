package com.roastmc.fabric.mixin.vanilla.world.entity;

import com.roastmc.fabric.injected.world.entity.ContextItemBasedSteering;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.ItemBasedSteering;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemBasedSteering.class)
public class MixinItemBasedSteering implements ContextItemBasedSteering {

    @Shadow
    private boolean boosting;

    @Shadow
    private int boostTime;

    @Shadow
    private SynchedEntityData entityData;

    @Shadow
    private EntityDataAccessor<Integer> boostTimeAccessor;

    @Override
    public void setBoostTicks(int ticks) {
        this.boosting = true;
        this.boostTime = 0;
        this.entityData.set(this.boostTimeAccessor, ticks);
    }
}
