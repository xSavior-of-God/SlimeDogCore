package dev.ratas.slimedogcore.impl.utils.logging;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.impl.utils.logging.DebugLoggerTest.DelegatedLogger;

public class DisallowWithTimeStrategyTest {
    private static final long BLOCK_MS = 100L;
    private static final long CLEAR_CACHE_MS = 4L * BLOCK_MS;
    private static final DelegatedLogger DELEGATE = new DelegatedLogger();
    private DisallowWithinTimeStrategy spamStrategy;
    private DebugLogger logger;

    @BeforeEach
    public void setup() {
        DELEGATE.consumer = null;
        spamStrategy = new DisallowWithinTimeStrategy(BLOCK_MS, CLEAR_CACHE_MS);
        logger = new DebugLogger(DELEGATE, () -> true, spamStrategy, "TESTDEBUG: ");
    }

    @Test
    public void test_allowsDifferent() {
        String msg1 = "some 2 message";
        String msg2 = "some 3 other message";
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
    public void test_blocksSame() {
        String msg = "some (same) message";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (record) -> {
            nr.incrementAndGet();
            Assertions.assertEquals(logger.getPrefix() + msg, record.getMessage());
        };
        logger.log(msg);
        logger.log(msg);
        logger.log(msg);
        Assertions.assertEquals(1, nr.get(), "Expected to be called only for the first time");
    }

    @Test
    public void test_allowsNewAfterBlock() {
        String msg1 = "some (same) message";
        String msg2 = "some OTHER message";
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
        logger.log(msg1);
        logger.log(msg2);
        logger.log(msg2);
        Assertions.assertEquals(2, nr.get(), "Expected to be called once for each message");
    }

    @Test
    public void test_allowsSameAfterTime() throws InterruptedException {
        String msg = "some (same) message";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (record) -> {
            nr.incrementAndGet();
            Assertions.assertEquals(logger.getPrefix() + msg, record.getMessage());
        };
        logger.log(msg);
        TimeUnit.MILLISECONDS.sleep(BLOCK_MS + 2);
        logger.log(msg);
        TimeUnit.MILLISECONDS.sleep(BLOCK_MS + 2);
        logger.log(msg);
        Assertions.assertEquals(3, nr.get(), "Expected to be called all messages due to the sleep");
    }

    @Test
    public void test_blockBeforeAllowsAfterTime() throws InterruptedException {
        String msg = "some (same) message";
        AtomicInteger nr = new AtomicInteger(0);
        DELEGATE.consumer = (record) -> {
            nr.incrementAndGet();
            Assertions.assertEquals(logger.getPrefix() + msg, record.getMessage());
        };
        logger.log(msg);
        logger.log(msg);
        logger.log(msg);
        TimeUnit.MILLISECONDS.sleep(BLOCK_MS + 2);
        logger.log(msg);
        logger.log(msg);
        logger.log(msg);
        TimeUnit.MILLISECONDS.sleep(BLOCK_MS + 2);
        logger.log(msg);
        logger.log(msg);
        logger.log(msg);
        Assertions.assertEquals(3, nr.get(), "Expected to be called only after sleeping");
    }

    @Test
    public void test_cacheStartsEmpty() {
        Assertions.assertTrue(spamStrategy.nextSendTimes.isEmpty(), "Should start with empty cache");
    }

    @Test
    public void test_cacheContainsMessage() {
        String msg = "a random message";
        logger.log(msg);
        Assertions.assertFalse(spamStrategy.nextSendTimes.isEmpty(), "Cache shouldn't be empty after message");
        Assertions.assertEquals(1, spamStrategy.nextSendTimes.size(), "Cache should have one entry");
        Assertions.assertEquals(msg, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Cache should have the message as the entry");
    }

    @Test
    public void test_cacheClearsAfterTime() throws InterruptedException {
        String msg1 = "a random message";
        logger.log(msg1);
        Assertions.assertFalse(spamStrategy.nextSendTimes.isEmpty(), "Cache shouldn't be empty after message");
        Assertions.assertEquals(1, spamStrategy.nextSendTimes.size(), "Cache should have one entry");
        Assertions.assertEquals(msg1, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Cache should have the message as the entry");
        TimeUnit.MILLISECONDS.sleep(CLEAR_CACHE_MS + 2);
        String msg2 = "flushing message";
        logger.log(msg2);
        Assertions.assertFalse(spamStrategy.nextSendTimes.isEmpty(), "Cache shouldn't be empty after message");
        Assertions.assertEquals(1, spamStrategy.nextSendTimes.size(), "Cache should have one entry");
        Assertions.assertNotEquals(msg1, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Shouldn't get old message which should have been cleared");
        Assertions.assertEquals(msg2, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Cache should have the message as the entry");
    }

    @Test
    public void test_cacheClearsAfterTimeMultiples() throws InterruptedException {
        String msg1 = "a random message";
        String msg2 = "(different) a random mess33age";
        String msg3 = "not same a random me22ssage";
        String msg4 = "- chages !! a random 11message";
        logger.log(msg1);
        logger.log(msg2);
        logger.log(msg3);
        logger.log(msg4);
        Assertions.assertFalse(spamStrategy.nextSendTimes.isEmpty(), "Cache shouldn't be empty after message");
        Assertions.assertEquals(4, spamStrategy.nextSendTimes.size(), "Cache should have one entry");
        TimeUnit.MILLISECONDS.sleep(CLEAR_CACHE_MS + 2);
        String flusher = "just so the cache gets ticked";
        logger.log(flusher);
        Assertions.assertFalse(spamStrategy.nextSendTimes.isEmpty(), "Cache shouldn't be empty after message");
        Assertions.assertEquals(1, spamStrategy.nextSendTimes.size(), "Cache should have one entry");
        Assertions.assertNotEquals(msg1, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Shouldn't get old message (1) which should have been cleared");
        Assertions.assertNotEquals(msg2, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Shouldn't get old message (2) which should have been cleared");
        Assertions.assertNotEquals(msg3, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Shouldn't get old message (3) which should have been cleared");
        Assertions.assertNotEquals(msg4, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Shouldn't get old message (4) which should have been cleared");
        Assertions.assertEquals(flusher, spamStrategy.nextSendTimes.keySet().iterator().next(),
                "Cache should have the message as the entry");
    }

}
