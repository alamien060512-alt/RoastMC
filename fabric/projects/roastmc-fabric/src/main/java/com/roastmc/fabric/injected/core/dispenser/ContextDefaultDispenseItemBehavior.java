package com.roastmc.fabric.injected.core.dispenser;

import com.roastmc.fabric.util.ContextStateException;

/**
 * 对应 CraftBukkit 为 DefaultDispenseItemBehavior 增加的 dropper 构造标记。
 * CraftBukkit: new DefaultDispenseItemBehavior(boolean dropper) — DropperBlock 使用。
 */
public interface ContextDefaultDispenseItemBehavior {

    default void roastmc$dropper(boolean dropper) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$dropper() {
        throw new ContextStateException("Not implemented");
    }
}
