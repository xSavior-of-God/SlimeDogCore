package dev.ratas.slimedogcore.api.scheduler;

import java.util.function.Consumer;

/**
 * Serves as wrapper for {@link org.bukkit.scheduler.BukkitScheduler}.
 */
public interface SDCScheduler {

    /**
     * Allows running a task as soon as possible (in sync).
     *
     * @param runnable the task to be run
     */
    void runTask(Runnable runnable);

    /**
     * Allows running a task as soon as possible (async).
     *
     * @param runnable the task to be run
     */
    void runTaskAsync(Runnable runnable);

    /**
     * Allows running a task later (in sync).
     *
     * @param runnable   the task to be run
     * @param delayTicks the delay for the task
     */
    void runTaskLater(Runnable runnable, long delayTicks);

    /**
     * Allows running a task later (async).
     *
     * @param runnable   the task to be run
     * @param delayTicks the delay for the task
     */
    void runTaskLaterAsync(Runnable runnable, long delayTicks);

    /**
     * Allows running a task timer (in sync).
     *
     * @param runnable the task to be run
     * @param delay    the delay for the task
     * @param period   the period between subsequent tasks
     */
    void runTaskTimer(Runnable runnable, long delay, long period);

    /**
     * Allows running a task timer (async).
     *
     * @param runnable the task to be run
     * @param delay    the delay for the task
     * @param period   the period between subsequent tasks
     */
    void runTaskTimerAsync(Runnable runnable, long delay, long period);

    /**
     * Allows running a task as soon as possible (in sync) using a consumer. The
     * benefit is that the task in the consumer can be used to cancel the task if
     * needed.
     *
     * @param consumer the task to be run
     */
    void runTask(Consumer<SDCTask> consumer);

    /**
     * Allows running a task as soon as possible (async) using a consumer. The
     * benefit is that the task in the consumer can be used to cancel the task if
     * needed.
     *
     * @param consumer the task to be run
     */
    void runTaskAsync(Consumer<SDCTask> consumer);

    /**
     * Allows running a task later (in sync) using a consumer. The benefit is that
     * the task in the consumer can be used to cancel the task if needed.
     *
     * @param consumer   the task to be run
     * @param delayTicks the delay for the task
     */
    void runTaskLater(Consumer<SDCTask> consumer, long delayTicks);

    /**
     * Allows running a task later (async) using a consumer. The benefit is that the
     * task in the consumer can be used to cancel the task if needed.
     *
     * @param consumer   the task to be run
     * @param delayTicks the delay for the task
     */
    void runTaskLaterAsync(Consumer<SDCTask> consumer, long delayTicks);

    /**
     * Allows running a task timer (in sync) using a consumer. The benefit is that
     * the task in the consumer can be used to cancel the task if needed.
     *
     * @param consumer the task to be run
     * @param delay    the delay for the task
     * @param period   the period between subsequent tasks
     */
    void runTaskTimer(Consumer<SDCTask> consumer, long delay, long period);

    /**
     * Allows running a task timer (async) using a consumer. The benefit is that the
     * task in the consumer can be used to cancel the task if needed.
     *
     * @param consumer the task to be run
     * @param delay    the delay for the task
     * @param period   the period between subsequent tasks
     */
    void runTaskTimerAsync(Consumer<SDCTask> consumer, long delay, long period);

}
