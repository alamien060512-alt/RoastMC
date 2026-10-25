package com.roastmc.fabric.injected.world.entity.ai.gossip;

import com.roastmc.fabric.util.ContextStateException;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.npc.villager.Villager;

/**
 * @author Mgazul
 * @date 2026/5/13 21:47
 */
public interface ContextGossipContainer {

    default GossipContainer villager(Villager villager) {
        throw new ContextStateException("Not implemented");
    }

    default int getReputation(UUID entity, Predicate<GossipType> types, boolean weighted) {
        throw new ContextStateException("Not implemented");
    }

    default void add(UUID target, GossipType type, int amountToAdd, org.bukkit.entity.Villager.ReputationEvent changeReason) {
        throw new ContextStateException("Not implemented");
    }

    default void set(UUID target, GossipType type, int amount, org.bukkit.entity.Villager.ReputationEvent changeReason) {
        throw new ContextStateException("Not implemented");
    }

    default void remove(UUID target, GossipType type, int amountToRemove, org.bukkit.entity.Villager.ReputationEvent changeReason) {
        throw new ContextStateException("Not implemented");
    }
}
