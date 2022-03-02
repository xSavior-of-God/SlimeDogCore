package dev.ratas.slimedogcore.impl.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DebugLoggerTest {
    private static final DelegatedLogger DELEGATE = new DelegatedLogger();
    private DebugLogger logger;
    private boolean isEnabled;

    @BeforeEach
    public void setup() {
        DELEGATE.consumer = null;
        logger = new DebugLogger(DELEGATE, () -> isEnabled);
    }

    @Test
    public void test_disabledLoggerNoCall() {
        isEnabled = false;
        String msg = "some random message";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (r) -> {
            nr.incrementAndGet();
        };
        logger.log(msg);
        Assertions.assertEquals(nr.get(), 0, "Expected the consumer not to be called");
    }

    @Test
    public void test_enabledLoggerCall() {
        isEnabled = true;
        String msg = "some random message (2)";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (r) -> {
            nr.incrementAndGet();
            Assertions.assertEquals(logger.getPrefix() + msg, r.getMessage(),
                    "Expected logged message to be equal to original");
        };
        logger.log(msg);
        Assertions.assertEquals(1, nr.get(), "Expected the consumer to be called exactly once");
    }

    @Test
    public void test_enabledLoggerWarning() {
        isEnabled = true;
        String msg = "some random message (3)";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (r) -> {
            nr.incrementAndGet();
            Assertions.assertEquals(Level.WARNING, r.getLevel(), "Expected logged message level to be WARNING");
        };
        logger.log(msg);
        Assertions.assertEquals(1, nr.get(), "Expected the consumer to be called exactly once");
    }

    private static final class DelegatedLogger extends Logger {
        private static final String NAME = "[Test]";
        private Consumer<LogRecord> consumer = null;

        protected DelegatedLogger() {
            super(NAME, null);
        }

        @Override
        public void log(LogRecord record) {
            if (consumer != null) {
                consumer.accept(record);
            }
            super.log(record);
        }

    }

}
