package dev.ratas.slimedogcore.impl.utils.logging;

import java.util.function.Supplier;
import java.util.logging.Logger;

import dev.ratas.slimedogcore.api.utils.logger.SDCDebugLogger;
import dev.ratas.slimedogcore.api.utils.logger.SDCSpamStrategy;

public class DebugLogger implements SDCDebugLogger {
    private static final String DEFAULT_PREFIX = "DEBUG: ";
    private final Logger delegate;
    private final SDCSpamStrategy spamStrategy;
    private final String prefix;
    private final Supplier<Boolean> enableChecker;

    public DebugLogger(Logger delegate, Supplier<Boolean> enableChecker) {
        this(delegate, enableChecker, DEFAULT_PREFIX);
    }

    public DebugLogger(Logger delegate, Supplier<Boolean> enableChecker, String prefix) {
        this(delegate, enableChecker, new AllowAllStrategy(), prefix);
    }

    public DebugLogger(Logger delegate, Supplier<Boolean> enableChecker, SDCSpamStrategy spamStrategy) {
        this(delegate, enableChecker, spamStrategy, DEFAULT_PREFIX);
    }

    public DebugLogger(Logger delegate, Supplier<Boolean> enableChecker, SDCSpamStrategy spamStrategy, String prefix) {
        this.delegate = delegate;
        this.enableChecker = enableChecker;
        this.spamStrategy = spamStrategy;
        this.prefix = prefix;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public boolean isEnabled() {
        return enableChecker.get();
    }

    @Override
    public void log(String msg) {
        if (isEnabled() && spamStrategy.shouldSend(msg)) {
            delegate.warning(prefix + msg);
        }
    }

}
