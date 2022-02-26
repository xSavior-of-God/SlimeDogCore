package dev.ratas.slimedogcore.impl.scheduler;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;
import dev.ratas.slimedogcore.api.scheduler.SDCTask;
import dev.ratas.slimedogcore.impl.SlimeDogCore;

public class TaskWrapper implements BukkitTask, SDCTask {
    private final BukkitTask delegate;
    private final SlimeDogCore plugin;

    public TaskWrapper(SlimeDogCore plugin, BukkitTask delegate) {
        this.plugin = plugin;
        this.delegate = delegate;
    }

    @Override
    public int getTaskId() {
        return delegate.getTaskId();
    }

    @Override
    public Plugin getOwner() {
        return plugin;
    }

    @Override
    public boolean isSync() {
        return delegate.isSync();
    }

    @Override
    public boolean isCancelled() {
        return delegate.isCancelled();
    }

    @Override
    public void cancel() {
        delegate.cancel();
    }

    @Override
    public SlimeDogPlugin getOwningPlugin() {
        return plugin;
    }

}
