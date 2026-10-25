package com.roastmc.fabric.mixin.vanilla.world.entity.ai.gossip;

import com.roastmc.fabric.injected.world.entity.ai.gossip.ContextGossipContainerEntityGossips;
import net.minecraft.world.entity.ai.gossip.GossipContainer;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(GossipContainer.EntityGossips.class)
public class MixinGossipContainer$EntityGossips implements ContextGossipContainerEntityGossips {
}
