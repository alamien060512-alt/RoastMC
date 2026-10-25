package com.roastmc.fabric.bukkit;

/**
 * @author Mgazul
 * @date 2026/3/28 03:34
 */
public class BukkitCallbackExecutor implements java.util.concurrent.Executor, Runnable {

    private final java.util.Queue<Runnable> queue = new java.util.ArrayDeque<>();

    @Override
    public void execute(Runnable runnable) {
        queue.add(runnable);
    }

    @Override
    public void run() {
        Runnable task;
        while ((task = queue.poll()) != null) {
            task.run();
        }
    }
}
