package com.roastmc.neoforge.feature;

import com.roastmc.neoforge.api.gui.GuiListener;
import com.roastmc.neoforge.commands.HatCommand;
import com.roastmc.neoforge.commands.HideAllCommand;
import com.roastmc.neoforge.commands.HideCommand;
import com.roastmc.neoforge.commands.OpenInvCommand;
import com.roastmc.neoforge.commands.VanishCommand;
import com.roastmc.neoforge.feature.back.BackCommands;
import com.roastmc.neoforge.feature.back.BackConfig;
import com.roastmc.neoforge.feature.ban.BanListener;
import com.roastmc.neoforge.feature.commands.CommandsConfig;
import com.roastmc.neoforge.feature.db.DatabaseManager;
import com.roastmc.neoforge.feature.entitylimits.EntityLimitsCommands;
import com.roastmc.neoforge.feature.item.ItemsConfig;
import com.roastmc.neoforge.feature.menu.MenuCommand;
import com.roastmc.neoforge.feature.menu.MenuListener;
import com.roastmc.neoforge.feature.plugin.PluginHotReloadCommand;
import com.roastmc.neoforge.feature.tpa.TpaComamands;
import com.roastmc.neoforge.feature.tpa.TpacceptCommands;
import com.roastmc.neoforge.feature.tpa.TpadenyCommands;
import com.roastmc.neoforge.feature.warps.WarpsCommands;
import com.roastmc.neoforge.feature.warps.WarpsConfig;
import com.roastmc.neoforge.feature.world.WorldManage;
import com.roastmc.neoforge.feature.world.commands.WorldsCommands;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.command.Command;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author Mgazul by MohistMC
 * @date 2023/6/14 14:46:34
 */
public class RoastMCPlugin {

    public static Logger LOGGER = LogManager.getLogger("RoastMCPlugin");

    public static void init() {
        DatabaseManager.init();
        if (CommandsConfig.INSTANCE.enable("worlds.enable")) {
            WorldManage.onEnable();
        }
        ItemsConfig.init();
        BackConfig.init();
        WarpsConfig.init();
        EntityClear.start();
        WorldBackup.start();
        GlobalVariableSystem.register();
    }

    public static void registerCommands(Map<String, Command> commands) {
        CommandsConfig.init();
        if (CommandsConfig.INSTANCE.enable("worlds.enable")) {
            commands.put("worlds", new WorldsCommands("worlds"));
        }
        if (CommandsConfig.INSTANCE.enable("warps.enable")) {
            commands.put("warps", new WarpsCommands("warps"));
        }
        if (CommandsConfig.INSTANCE.enable("tpa.enable")) {
            commands.put("tpa", new TpaComamands("tpa"));
            commands.put("tpadeny", new TpadenyCommands("tpadeny"));
            commands.put("tpaccept", new TpacceptCommands("tpaccept"));
        }
        if (CommandsConfig.INSTANCE.enable("back.enable")) {
            commands.put("back", new BackCommands("back"));
        }
        if (CommandsConfig.INSTANCE.enable("menus.enable")) {
            commands.put("menus", new MenuCommand("menus"));
        }
        if (CommandsConfig.INSTANCE.enable("hideall.enable")) {
            commands.put("hideall", new HideAllCommand("hideall"));
        }
        if (CommandsConfig.INSTANCE.enable("hide.enable")) {
            commands.put("hide", new HideCommand("hide"));
        }
        if (CommandsConfig.INSTANCE.enable("hat.enable")) {
            commands.put("hat", new HatCommand("hat"));
        }
        if (CommandsConfig.INSTANCE.enable("vanish.enable")) {
            commands.put("vanish", new VanishCommand("vanish"));
        }
        if (CommandsConfig.INSTANCE.enable("openinv.enable")) {
            commands.put("openinv", new OpenInvCommand("openinv"));
        }
        if (CommandsConfig.INSTANCE.enable("entitylimits.enable")) {
            commands.put("entitylimits", new EntityLimitsCommands("entitylimits"));
        }
        commands.put("plugin", new PluginHotReloadCommand("plugin"));

    }

    public static void registerListener(Event event) {
        if (event instanceof InventoryClickEvent inventoryClickEvent) {
            GuiListener.onInventoryClickEvent(inventoryClickEvent);
        }
        if (event instanceof PrepareAnvilEvent prepareAnvilEvent) {
            EnchantmentFix.anvilListener(prepareAnvilEvent);
        }
        if (event instanceof InventoryCloseEvent event1) {
            BanListener.save(event1);
            GuiListener.onInventoryCloseEvent(event1);
        }
        if (event instanceof PlayerTeleportEvent event1) {
            BackCommands.hookTeleport(event1);
        }
        if (event instanceof PlayerDeathEvent event1) {
            BackCommands.hooktDeath(event1);
        }
        if (event instanceof PlayerInteractEvent event1) {
            if (CommandsConfig.INSTANCE.enable("menus.enable")) {
                MenuListener.onPlayerInteract(event1);
            }
        }
    }
}
