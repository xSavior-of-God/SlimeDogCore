package dev.ratas.slimedogcore.impl.scheduler;

import java.util.function.Consumer;

import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import dev.ratas.slimedogcore.api.scheduler.SDCScheduler;
import dev.ratas.slimedogcore.api.scheduler.SDCTask;
import dev.ratas.slimedogcore.impl.SlimeDogCore;

public class Scheduler implements SDCScheduler {
    private final SlimeDogCore plugin;

    public Scheduler(SlimeDogCore plugin) {
        this.plugin = plugin;
    }

    private BukkitScheduler getScheduler() {
        return plugin.getServer().getScheduler();
    }

    @Override
    public void runTask(Runnable runnable) {
        getScheduler().runTask(plugin, runnable);
    }

    @Override
    public void runTaskAsync(Runnable runnable) {
        getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    @Override
    public void runTaskLater(Runnable runnable, long delayTicks) {
        getScheduler().runTaskLater(plugin, runnable, delayTicks);
    }

    @Override
    public void runTaskLaterAsync(Runnable runnable, long delayTicks) {
        getScheduler().runTaskLaterAsynchronously(plugin, runnable, delayTicks);
    }

    @Override
    public void runTaskTimer(Runnable runnable, long delay, long period) {
        getScheduler().runTaskTimer(plugin, runnable, delay, period);
    }

    @Override
    public void runTaskTimerAsync(Runnable runnable, long delay, long period) {
        getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, period);
    }

    private Consumer<BukkitTask> convert(Consumer<SDCTask> consumer) {
        return (t) -> consumer.accept(new TaskWrapper(plugin, t));
    }

    @Override
    public void runTask(Consumer<SDCTask> consumer) {
        getScheduler().runTask(plugin, convert(consumer));
    }

    @Override
    public void runTaskAsync(Consumer<SDCTask> consumer) {
        getScheduler().runTaskAsynchronously(plugin, convert(consumer));
    }

    @Override
    public void runTaskLater(Consumer<SDCTask> consumer, long delayTicks) {
        getScheduler().runTaskLater(plugin, convert(consumer), delayTicks);
    }

    @Override
    public void runTaskLaterAsync(Consumer<SDCTask> consumer, long delayTicks) {
        getScheduler().runTaskLaterAsynchronously(plugin, convert(consumer), delayTicks);
    }

    @Override
    public void runTaskTimer(Consumer<SDCTask> consumer, long delay, long period) {
        getScheduler().runTaskTimer(plugin, convert(consumer), delay, period);
    }

    @Override
    public void runTaskTimerAsync(Consumer<SDCTask> consumer, long delay, long period) {
        getScheduler().runTaskTimerAsynchronously(plugin, convert(consumer), delay, period);
    }

}
