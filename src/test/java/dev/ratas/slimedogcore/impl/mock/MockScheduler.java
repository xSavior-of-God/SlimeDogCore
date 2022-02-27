package dev.ratas.slimedogcore.impl.mock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;
import dev.ratas.slimedogcore.api.scheduler.SDCScheduler;
import dev.ratas.slimedogcore.api.scheduler.SDCTask;

public class MockScheduler implements SDCScheduler {
    public final Queue<TaskInfo> currentTasks = new LinkedList<>();
    public final List<TaskInfo> laterTasks = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public void tick() {
        while (!currentTasks.isEmpty()) {
            TaskInfo task = currentTasks.poll();
            if (task.task instanceof Runnable) {
                ((Runnable) task.task).run();
            } else if (task.task instanceof Consumer) {
                // "unchecked" cast
                ((Consumer<SDCTask>) task.task).accept(new MockTask(-1));
            }
        }
        List<TaskInfo> moved = new ArrayList<>();
        for (TaskInfo info : laterTasks) {
            info.waitFor--;
            if (info.waitFor <= 0) {
                moved.add(info);
                currentTasks.add(info);
            }
        }
    }

    @Override
    public void runTask(Runnable runnable) {
        currentTasks.add(new TaskInfo(runnable));
    }

    @Override
    public void runTaskAsync(Runnable runnable) {
        currentTasks.add(new TaskInfo(runnable));
    }

    @Override
    public void runTaskLater(Runnable runnable, long delayTicks) {
        currentTasks.add(new TaskInfo(runnable, delayTicks));
    }

    @Override
    public void runTaskLaterAsync(Runnable runnable, long delayTicks) {
        currentTasks.add(new TaskInfo(runnable, delayTicks));
    }

    @Override
    public void runTaskTimer(Runnable runnable, long delay, long period) {
        currentTasks.add(new TaskInfo(runnable, delay));
    }

    @Override
    public void runTaskTimerAsync(Runnable runnable, long delay, long period) {
        currentTasks.add(new TaskInfo(runnable, delay));
    }

    @Override
    public void runTask(Consumer<SDCTask> consumer) {
        currentTasks.add(new TaskInfo(consumer));
    }

    @Override
    public void runTaskAsync(Consumer<SDCTask> consumer) {
        currentTasks.add(new TaskInfo(consumer));
    }

    @Override
    public void runTaskLater(Consumer<SDCTask> consumer, long delayTicks) {
        currentTasks.add(new TaskInfo(consumer, delayTicks));
    }

    @Override
    public void runTaskLaterAsync(Consumer<SDCTask> consumer, long delayTicks) {
        currentTasks.add(new TaskInfo(consumer, delayTicks));
    }

    @Override
    public void runTaskTimer(Consumer<SDCTask> consumer, long delay, long period) {
        currentTasks.add(new TaskInfo(consumer, delay));
    }

    @Override
    public void runTaskTimerAsync(Consumer<SDCTask> consumer, long delay, long period) {
        currentTasks.add(new TaskInfo(consumer, delay));
    }

    public static final class TaskInfo {
        private final Object task;
        private long waitFor;

        private TaskInfo(Object task) {
            this(task, 0L);
        }

        private TaskInfo(Object task, long waitFor) {
            this.task = task;
            this.waitFor = waitFor;
        }
    }

    public final class MockTask implements SDCTask {
        private final int id;

        public MockTask(int id) {
            this.id = id;
        }

        @Override
        public void cancel() {
        }

        @Override
        public SlimeDogPlugin getOwningPlugin() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int getTaskId() {
            return id;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isSync() {
            return true;
        }

    }

}
