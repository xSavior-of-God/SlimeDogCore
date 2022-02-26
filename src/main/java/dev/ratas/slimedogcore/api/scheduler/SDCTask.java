package dev.ratas.slimedogcore.api.scheduler;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;

/**
 * This is a wrapper for {@link org.bukkit.scheduler.BukkitTask}.
 * SDC is shorthand for SlimeDogCore.
 */
public interface SDCTask {

    /**
     * Cancels the current task
     */
    void cancel();

    /**
     * Gets the owning plugin for this tassk
     *
     * @return the owning plugin
     */
    SlimeDogPlugin getOwningPlugin();

    /**
     * Gets the tassk ID for this task. This will be equivelant to the Bukkit task
     * ID.
     *
     * @return task ID
     */
    int getTaskId();

    /**
     * Checks if the task is cancelled.
     *
     * @return whether or not the task is cancelled
     */
    boolean isCancelled();

    /**
     * Checks whether the task is run synchronously or asynchronously
     *
     * @return true if synchronous, flase otherwise
     */
    boolean isSync();

}
