package dev.ratas.slimedogcore.impl.utils.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dev.ratas.slimedogcore.api.utils.logger.SDCSpamStrategy;

public class DisallowWithinTimeStrategy implements SDCSpamStrategy {
    final Map<String, Long> nextSendTimes = new ConcurrentHashMap<>();
    private final long blockForMs;
    private final long clearCacheMs;
    private long nextClear;

    public DisallowWithinTimeStrategy(long blockForMs, long clearCacheMs) {
        this.blockForMs = blockForMs;
        this.clearCacheMs = clearCacheMs;
        this.nextClear = System.currentTimeMillis() + clearCacheMs;
    }

    private void tickClearing(long current) {
        if (current > nextClear) {
            clearCache(current);
            nextClear = current + clearCacheMs;
        }
    }

    private void clearCache(long current) {
        nextSendTimes.values().removeIf(nextShow -> nextShow < current); // next show in the past
    }

    private boolean shouldBlock(long current, String msg) {
        Long nextSendTime = nextSendTimes.get(msg);
        long futureNext = current + blockForMs;
        boolean shouldBlock;
        if (nextSendTime == null || nextSendTime < current) {
            shouldBlock = false;
        } else {
            shouldBlock = true;
        }
        nextSendTimes.put(msg, futureNext);
        return shouldBlock;
    }

    @Override
    public boolean shouldSend(String msg) {
        long current = System.currentTimeMillis();
        tickClearing(current);
        return !shouldBlock(current, msg);
    }

}
