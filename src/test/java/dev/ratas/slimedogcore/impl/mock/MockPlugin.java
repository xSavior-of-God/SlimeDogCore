package dev.ratas.slimedogcore.impl.mock;

import java.io.File;
import java.util.logging.Logger;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;
import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;
import dev.ratas.slimedogcore.api.config.settings.SDCBaseSettings;
import dev.ratas.slimedogcore.api.scheduler.SDCScheduler;
import dev.ratas.slimedogcore.api.utils.logger.SDCDebugLogger;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginInformation;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginManager;
import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;
import dev.ratas.slimedogcore.api.wrappers.SDCWorldProvider;
import dev.ratas.slimedogcore.impl.config.BaseSettings;
import dev.ratas.slimedogcore.impl.config.ConfigManager;
import dev.ratas.slimedogcore.impl.utils.logging.DebugLogger;

public class MockPlugin implements SlimeDogPlugin {
    public static final Logger LOGGER = Logger.getLogger("[TEST] SDC");
    public final ConfigManager configManager = new ConfigManager(this, false); // do not save configs
    public final MockPluginManager pluginManager = new MockPluginManager();
    public final MockWorldProvider worldProvider = new MockWorldProvider();
    public final MockScheduler scheduler = new MockScheduler();
    public final MockPluginInformation pluginInformation = new MockPluginInformation();
    public final File dataFolder;
    public final MockResourceProvider resourceProvider = new MockResourceProvider();
    public final DebugLogger debugLogger = new DebugLogger(LOGGER, () -> true);
    public final BaseSettings baseSettings = new BaseSettings(() -> getDefaultConfig());

    public MockPlugin() {
        this(new File("."));
    }

    public MockPlugin(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    @Override
    public File getDataFolder() {
        return dataFolder;
    }

    @Override
    public File getWorldFolder() {
        return dataFolder;
    }

    @Override
    public SDCScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public SDCPluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public SDCWorldProvider getWorldProvider() {
        return worldProvider;
    }

    @Override
    public SDCResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    @Override
    public SDCCustomConfigManager getCustomConfigManager() {
        return configManager;
    }

    @Override
    public SDCPluginInformation getPluginInformation() {
        return pluginInformation;
    }

    @Override
    public void pluginEnabled() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pluginDisabled() {
        // TODO Auto-generated method stub

    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public SDCDebugLogger getDebugLogger() {
        return debugLogger;
    }

    @Override
    public SDCBaseSettings getBaseSettings() {
        return baseSettings;
    }

}
