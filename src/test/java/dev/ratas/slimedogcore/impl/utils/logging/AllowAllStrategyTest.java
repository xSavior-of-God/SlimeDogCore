package dev.ratas.slimedogcore.impl.utils.logging;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.impl.utils.logging.DebugLoggerTest.DelegatedLogger;

public class AllowAllStrategyTest {
    private static final DelegatedLogger DELEGATE = new DelegatedLogger();
    private AllowAllStrategy spamStrategy;
    private DebugLogger logger;

    @BeforeEach
    public void setup() {
        DELEGATE.consumer = null;
        spamStrategy = new AllowAllStrategy();
        logger = new DebugLogger(DELEGATE, () -> true, spamStrategy, "TESTDEBUG: ");
    }

    @Test
    public void test_allowsDifferent() {
        String msg1 = "some message";
        String msg2 = "some other message";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (record) -> {
            int cur = nr.incrementAndGet();
            if (cur == 1) {
                Assertions.assertEquals(logger.getPrefix() + msg1, record.getMessage());
            } else {
                Assertions.assertEquals(logger.getPrefix() + msg2, record.getMessage());
            }
        };
        logger.log(msg1);
        logger.log(msg2);
        Assertions.assertEquals(2, nr.get(), "Expected to be called for both messages");
    }

    @Test
    public void test_allowsSame() {
        String msg = "some message";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (record) -> {
            nr.incrementAndGet();
            Assertions.assertEquals(logger.getPrefix() + msg, record.getMessage());
        };
        logger.log(msg);
        logger.log(msg);
        logger.log(msg);
        Assertions.assertEquals(3, nr.get(), "Expected to be called each time (3 times)");
    }

}
