package dev.ratas.slimedogcore.impl;

import java.util.function.Supplier;
import java.util.logging.Logger;

import dev.ratas.slimedogcore.api.SDCDebugLogger;

public class DebugLogger implements SDCDebugLogger {
    private static final String DEFAULT_PREFIX = "DEBUG: ";
    private final Logger delegate;
    private final String prefix;
    private final Supplier<Boolean> enableChecker;

    public DebugLogger(Logger delegate, Supplier<Boolean> enableChecker) {
        this(delegate, enableChecker, DEFAULT_PREFIX);
    }

    public DebugLogger(Logger delegate, Supplier<Boolean> enableChecker, String prefix) {
        this.delegate = delegate;
        this.enableChecker = enableChecker;
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
        if (isEnabled()) {
            delegate.warning(prefix + msg);
        }
    }

}
