package com.roastmc.fabric.bukkit;

import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.server.permissions.Permissions;

/**
 * @author Mgazul
 * @date 2026/3/28 04:16
 */
public class DelegatePermissionSet implements PermissionSet {

    private final CommandSourceStack commandSourceStack;
    private final PermissionSet handle;

    public DelegatePermissionSet(CommandSourceStack commandSourceStack, PermissionSet handle) {
        this.commandSourceStack = commandSourceStack;
        this.handle = handle;
    }

    @Override
    public boolean hasPermission(Permission permission) {
        boolean hasPermission = handle.hasPermission(permission);

        CommandNode currentCommand = commandSourceStack.roastmc$currentCommand();
        if (currentCommand != null) {
            return hasPermission(hasPermission, org.bukkit.craftbukkit.command.VanillaCommandWrapper.getPermission(currentCommand));
        }

        if (permission.equals(Permissions.COMMANDS_ENTITY_SELECTORS)) {
            return hasPermission(hasPermission, "minecraft.command.selector");
        }

        return hasPermission;
    }

    public boolean hasPermission(boolean hasPermission, String bukkitPermission) {
        // World is null when loading functions
        return ((commandSourceStack.getLevel() == null || !commandSourceStack.getLevel().getCraftServer().ignoreVanillaPermissions) && hasPermission) || commandSourceStack.getBukkitSender().hasPermission(bukkitPermission);
    }
}
