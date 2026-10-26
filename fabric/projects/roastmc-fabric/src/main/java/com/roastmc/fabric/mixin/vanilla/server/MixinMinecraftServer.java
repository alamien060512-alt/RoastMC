package com.roastmc.fabric.mixin.vanilla.server;

import com.roastmc.fabric.bukkit.BukkitUtils;
import com.roastmc.fabric.injected.server.ContextMinecraftServer;
import io.izzel.arclight.mixin.TransformAccess;
import joptsimple.OptionSet;
import net.minecraft.commands.Commands;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldLoader;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.CraftServer;
import org.jline.terminal.Terminal;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/4/2 15:15
 */
@Mixin(MinecraftServer.class)
public class MixinMinecraftServer implements ContextMinecraftServer {

    // CraftBukkit start
    public WorldLoader.DataLoadContext worldLoader;
    public org.bukkit.craftbukkit.CraftServer server;
    public OptionSet options;
    public org.bukkit.command.ConsoleCommandSender console;
    public Terminal terminal;
    public static int currentTick = (int) (System.currentTimeMillis() / 50);
    public java.util.Queue<Runnable> processQueue = new java.util.concurrent.ConcurrentLinkedQueue<Runnable>();
    public int autosavePeriod;
    public Commands vanillaCommandDispatcher;
    private boolean forceTicks;
    // CraftBukkit end

    @Override
    public WorldLoader.DataLoadContext roastmc_worldLoader() {
        return worldLoader;
    }

    @Override
    public CraftServer roastmc$server() {
        return server;
    }

    @Override
    public Commands vanillaCommandDispatcher() {
        return vanillaCommandDispatcher;
    }

    @Override
    public OptionSet roastmc_options() {
       return options;
    }

    @Override
    public ConsoleCommandSender roastmc_console() {
        return console;
    }

    @Override
    public void roastmc_console(ConsoleCommandSender console) {
        this.console = console;
    }

    @Override
    public java.util.Queue<Runnable> roastmc_processQueue() {
        return processQueue;
    }

    @Override
    public int roastmc_autosavePeriod() {
        return autosavePeriod;
    }

    @Override
    public void roastmc_autosavePeriod(int autosavePeriod) {
        this.autosavePeriod = autosavePeriod;
    }

    @Unique
    @TransformAccess(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
    private static MinecraftServer getServer() {
        return BukkitUtils.getServer();
    }

    @Unique
    @TransformAccess(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
    private static RegistryAccess getDefaultRegistryAccess() {
        return BukkitUtils.getDefaultRegistryAccess();
    }
}
