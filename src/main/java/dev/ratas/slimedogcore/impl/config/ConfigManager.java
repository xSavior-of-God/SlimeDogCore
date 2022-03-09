package dev.ratas.slimedogcore.impl.config;

import java.io.File;
import java.nio.file.Path;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;
import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;

public class ConfigManager implements SDCCustomConfigManager {
    private static final String DEFAULT_CONFIG_NAME = "config.yml";
    private final SlimeDogPlugin plugin;
    private final boolean createFilesFromDefault;

    public ConfigManager(SlimeDogPlugin plugin) {
        this(plugin, true);
    }

    public ConfigManager(SlimeDogPlugin plugin, boolean createFilesFromDefault) {
        this.plugin = plugin;
        this.createFilesFromDefault = createFilesFromDefault;
    }

    @Override
    public SDCCustomConfig getConfig(String fileName) {
        return getConfig(new File(plugin.getDataFolder(), fileName));
    }

    @Override
    public SDCCustomConfig getConfig(File file) throws IllegalArgumentException {
        File dataFolder = plugin.getDataFolder();
        Path dataFolderPath = dataFolder.toPath().toAbsolutePath();
        Path filePath = file.toPath().toAbsolutePath();
        if (!filePath.startsWith(dataFolderPath)) {
            throw new IllegalArgumentException("Cannot use custom configs for files not in the plugin data folder");
        }
        return new CustomYamlConfig(plugin.getResourceProvider(), file, createFilesFromDefault);
    }

    @Override
    public SDCCustomConfig getDefaultConfig() {
        SDCCustomConfig config = getConfig(DEFAULT_CONFIG_NAME);
        config.saveDefaultConfig();
        return config;
    }

}
