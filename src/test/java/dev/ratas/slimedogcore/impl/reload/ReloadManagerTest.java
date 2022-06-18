package dev.ratas.slimedogcore.impl.reload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.reload.ReloadException;
import dev.ratas.slimedogcore.api.reload.SDCReloadable;

public class ReloadManagerTest {
    ReloadManager reloadManager;

    @BeforeEach
    public void setup() {
        reloadManager = new ReloadManager();
    }

    @Test
    public void test_ReloadManager_Builds() {
        Assertions.assertNotNull(reloadManager, "Reload manager should not be null");
    }

    @Test
    public void test_ReloadManager_noRegisterNoReload() {
        CountingReloadable reloadable = new CountingReloadable();
        Assertions.assertEquals(0, reloadable.count, "No count before reload");
        reloadManager.reload();
        Assertions.assertEquals(0, reloadable.count, "No count after reload without a registration");
    }

    @Test
    public void test_ReloadManager_registeredReloads() {
        CountingReloadable reloadable = new CountingReloadable();
        Assertions.assertEquals(0, reloadable.count, "No count before reload");
        reloadManager.register(reloadable);
        reloadManager.reload();
        Assertions.assertEquals(1, reloadable.count, "Count should be going up");
    }

    @Test
    public void test_registeringTwiceFails() {
        CountingReloadable reloadable = new CountingReloadable();
        reloadManager.register(reloadable);
        Assertions.assertThrows(IllegalStateException.class, () -> reloadManager.register(reloadable));
    }

    @Test
    public void test_registeringOrderMaintained() {
        CountingReloadable reloadable1 = new CountingReloadable();
        DelegatingCountingReloadable reloadable2 = new DelegatingCountingReloadable(
                () -> Assertions.assertEquals(1, reloadable1.count, "First one should be called first"));
        reloadManager.register(reloadable1);
        reloadManager.register(reloadable2);
        reloadManager.reload();
        Assertions.assertEquals(1, reloadable2.count, "Reloudable 2 should have been called");
    }

    public static class CountingReloadable implements SDCReloadable {
        public int count = 0;

        @Override
        public void reload() throws ReloadException {
            count++;
        }

    }

    public static class DelegatingCountingReloadable extends CountingReloadable {
        public final Runnable run;

        public DelegatingCountingReloadable(Runnable run) {
            this.run = run;
        }

        @Override
        public void reload() throws ReloadException {
            super.reload();
            run.run();
        }

    }

}
