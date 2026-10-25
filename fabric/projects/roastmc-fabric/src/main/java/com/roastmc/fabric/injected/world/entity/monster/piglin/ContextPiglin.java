package com.roastmc.fabric.injected.world.entity.monster.piglin;

import com.roastmc.fabric.util.ContextStateException;
import java.util.Set;
import net.minecraft.world.item.Item;

public interface ContextPiglin {

    default Set<Item> roastmc$allowedBarterItems() {
        throw new ContextStateException("Not implemented");
    }

    default Set<Item> roastmc$interestItems() {
        throw new ContextStateException("Not implemented");
    }
}
