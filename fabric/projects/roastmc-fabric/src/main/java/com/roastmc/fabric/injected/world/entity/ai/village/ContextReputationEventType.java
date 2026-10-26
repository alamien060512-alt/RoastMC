package com.roastmc.fabric.injected.world.entity.ai.village;

import net.minecraft.world.entity.ai.village.ReputationEventType;

/**
 * @author Mgazul
 * @date 2026/5/13 21:12
 */
public interface ContextReputationEventType {

    java.util.Map<String, ReputationEventType> BY_ID = com.google.common.collect.Maps.newHashMap(); // CraftBukkit - map with all values
    // CraftBukkit start - additional events added in the API
    ReputationEventType GOSSIP = ReputationEventType.register("bukkit_gossip");
    ReputationEventType DECAY = ReputationEventType.register("bukkit_decay");
    ReputationEventType UNSPECIFIED = ReputationEventType.register("bukkit_unspecified");
    // CraftBukkit end
}
