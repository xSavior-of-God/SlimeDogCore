package dev.ratas.slimedogcore.impl.config;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.impl.mock.MockPlugin;

public class ConfigManagerTest {
    private MockPlugin mockPlugin;
    private ConfigManager manager;

    @BeforeEach
    public void setup() {
        mockPlugin = new MockPlugin();
        manager = mockPlugin.configManager;
    }

    @Test
    public void test_cannotGetFileOutsideDataFolder() {
        File parentFolder = mockPlugin.getDataFolder().getParentFile();
        File fileWithinParent = new File(parentFolder, "some_file.yml");
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.getConfig(fileWithinParent));
    }

    @Test
    public void test_canGetFileInsideDataFolder() {
        File file = new File(mockPlugin.getDataFolder(), "sf.yml");
        SDCCustomConfig config = manager.getConfig(file);
        Assertions.assertNotNull(config, "Config shouldn't be null");
    }

    @Test
    public void test_emptyConfigNoContents() {
        File file = new File(mockPlugin.getDataFolder(), "sf.yml");
        SDCCustomConfig config = manager.getConfig(file);
        Assertions.assertEquals(0, config.getConfig().getKeys(true).size(),
                "There should be no keys in a non-existing config");
        Assertions.assertEquals(0, config.getConfig().getValues(true).size(),
                "There should be no values in a non-existing config");
    }

    @Test
    public void test_emptyDefaultConfig() {
        SDCCustomConfig config = manager.getDefaultConfig();
        Assertions.assertEquals(0, config.getConfig().getKeys(true).size(),
                "There should be no keys in a non-existing (default) config");
        Assertions.assertEquals(0, config.getConfig().getValues(true).size(),
                "There should be no values in a non-existing (default) config");
    }

    @Test
    public void test_configManagerDifferentNameDifferentInstance() {
        SDCCustomConfig c1 = manager.getConfig("c1.yml");
        SDCCustomConfig c2 = manager.getConfig("c2.yml");
        Assertions.assertNotSame(c1, c2, "Different files should refer to different instances");
    }

    @Test
    public void test_configManagerSameNameSameInstance() {
        SDCCustomConfig c1_1 = manager.getConfig("c1.yml");
        SDCCustomConfig c1_2 = manager.getConfig("c1.yml");
        Assertions.assertSame(c1_1, c1_2, "Same file name should refer to the same instance");
    }

}
