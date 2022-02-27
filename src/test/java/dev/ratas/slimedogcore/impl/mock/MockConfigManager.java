package dev.ratas.slimedogcore.impl.mock;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;

public class MockConfigManager implements SDCCustomConfigManager {
    public final Map<String, SDCCustomConfig> configs = new HashMap<>();

    @Override
    public SDCCustomConfig getConfig(String fileName) {
        return configs.get(fileName);
    }

    @Override
    public SDCCustomConfig getConfig(File file) {
        return getConfig(file.getName());
    }

    @Override
    public SDCCustomConfig getDefaultConfig() {
        return getConfig("config.yml");
    }

}
