package com.roastmc.fabric.mixin.vanilla.world.entity.ai.gossip;

import com.roastmc.fabric.injected.world.entity.ai.gossip.ContextGossipContainer;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.npc.villager.Villager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/5/13 21:49
 */
@Mixin(GossipContainer.class)
public class MixinGossipContainer implements ContextGossipContainer {

    @Shadow @Final public Map<UUID, GossipContainer.EntityGossips> gossips;

    // CraftBukkit start - store reference to villager entity
    public Villager villager;

    public GossipContainer villager(Villager villager) {
        this.villager = villager;
        return (GossipContainer) (Object) this;
    }

    public int getReputation(UUID entity, Predicate<GossipType> types, boolean weighted) {
        // CraftBukkit end
        GossipContainer.EntityGossips entry = this.gossips.get(entity);
        // CraftBukkit start - handle weighted parameter
        return entry != null ? (weighted ? entry.weightedValue(types) : entry.unweightedValue(types)) : 0;
        // CraftBukkit end
    }

    public void add(UUID target, GossipType type, int amountToAdd, org.bukkit.entity.Villager.ReputationEvent changeReason) {
    }

    // CraftBukkit start
    public void set(UUID target, GossipType type, int amount, org.bukkit.entity.Villager.ReputationEvent changeReason) {
        int addAmount = amount - getReputation(target, Predicate.isEqual(type), false);
        if (addAmount == 0) {
            return;
        }
        this.add(target, type, addAmount, changeReason);
    }
    // CraftBukkit end

    // CraftBukkit start - add change reason parameter
    public void remove(UUID target, GossipType type, int amountToRemove, org.bukkit.entity.Villager.ReputationEvent changeReason) {
        this.add(target, type, -amountToRemove, changeReason);
    }
    // CraftBukkit end
}
