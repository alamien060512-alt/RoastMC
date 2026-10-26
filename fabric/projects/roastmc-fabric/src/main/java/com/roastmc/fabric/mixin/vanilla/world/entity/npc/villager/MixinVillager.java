package com.roastmc.fabric.mixin.vanilla.world.entity.npc.villager;

import com.roastmc.fabric.injected.world.entity.npc.villager.ContextVillager;
import net.minecraft.world.entity.npc.villager.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Villager.class)
public class MixinVillager implements ContextVillager {

    @Shadow
    public long gossipDecayInterval;

    @Override
    public long roastmc$gossipDecayInterval() {
        return gossipDecayInterval;
    }

    @Override
    public void roastmc$gossipDecayInterval(long gossipDecayInterval) {
        this.gossipDecayInterval = gossipDecayInterval;
    }
}
