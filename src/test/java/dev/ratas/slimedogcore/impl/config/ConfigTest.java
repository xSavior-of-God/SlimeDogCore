package dev.ratas.slimedogcore.impl.config;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;
import dev.ratas.slimedogcore.impl.mock.MockPlugin;

public class ConfigTest {
    private MockPlugin mockPlugin;
    private SDCCustomConfigManager manager;

    @BeforeEach
    public void setup() {
        mockPlugin = new MockPlugin();
        manager = new ConfigManager(mockPlugin);
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
        Assertions.assertEquals(0, config.getConfig().getKeys(true).size(),
                "There should be no keys in a non-existing config");
        Assertions.assertEquals(0, config.getConfig().getValues(true).size(),
                "There should be no values in a non-existing config");
    }

}
