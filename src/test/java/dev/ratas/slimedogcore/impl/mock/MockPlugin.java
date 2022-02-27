package dev.ratas.slimedogcore.impl.mock;

import java.io.File;
import java.util.logging.Logger;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;
import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;
import dev.ratas.slimedogcore.api.scheduler.SDCScheduler;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginInformation;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginManager;
import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;
import dev.ratas.slimedogcore.api.wrappers.SDCWorldProvider;

public class MockPlugin implements SlimeDogPlugin {
    public static final Logger LOGGER = Logger.getLogger("[TEST] SDC");
    private final MockConfigManager configManager = new MockConfigManager();
    private final MockPluginManager pluginManager = new MockPluginManager();
    private final MockWorldProvider worldProvider = new MockWorldProvider();
    private final MockScheduler scheduler = new MockScheduler();
    private final MockPluginInformation pluginInformation = new MockPluginInformation();
    private final File dataFolder;
    private final MockResourceProvider resourceProvider = new MockResourceProvider();

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

}
