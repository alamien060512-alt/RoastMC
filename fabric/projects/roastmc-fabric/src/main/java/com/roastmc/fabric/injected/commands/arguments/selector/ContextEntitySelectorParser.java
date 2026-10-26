package com.roastmc.fabric.injected.commands.arguments.selector;

import com.roastmc.fabric.util.ContextStateException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.arguments.selector.EntitySelector;

public interface ContextEntitySelectorParser {

    default EntitySelector parse(boolean overridePermissions) throws CommandSyntaxException {
        throw new ContextStateException("Not implemented");
    }

    default void parseSelector(boolean overridePermissions) throws CommandSyntaxException {
        throw new ContextStateException("Not implemented");
    }
}
