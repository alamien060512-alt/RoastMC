package com.roastmc.fabric.injected.network.chat;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.ChatFormatting;

public interface ContextTextColor {

    default ChatFormatting roastmc$format() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setFormat(ChatFormatting format) {
        throw new ContextStateException("Not implemented");
    }
}
