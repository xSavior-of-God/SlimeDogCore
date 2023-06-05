package dev.ratas.slimedogcore.impl.utils;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;
import dev.ratas.slimedogcore.impl.mock.MockPlugin;

public class UpdateCheckerTest {
    // example Spigot ID
    private static final int ENTITYCOUNT_SPIGOT_ID = 527767;
    // example Hangar author / slug
    private static final String ENTITYCOUNT_HANGAR_AUTHOR = "SlimeDog";
    private static final String ENTITYCOUNT_HANGAR_SLUG = "EntityCount";
    private SlimeDogPlugin plugin;

    @BeforeEach
    public void setUp() {
        plugin = new MockPlugin();
    }

    @Test
    public void test_UpdateChecker_works_SpigotMC() {
        AtomicInteger calls = new AtomicInteger(0);
        UpdateChecker.forSpigot(plugin, (response, version) -> {
            calls.incrementAndGet();
        }, ENTITYCOUNT_SPIGOT_ID).check(true);
        Assertions.assertEquals(calls.get(), 1);
    }

    @Test
    public void test_UpdateChecker_works_Hangar() {
        AtomicInteger calls = new AtomicInteger(0);
        UpdateChecker.forHangar(plugin, (response, version) -> {
            calls.incrementAndGet();
        }, ENTITYCOUNT_HANGAR_AUTHOR, ENTITYCOUNT_HANGAR_SLUG).check(true);
        Assertions.assertEquals(calls.get(), 1);
    }

}
