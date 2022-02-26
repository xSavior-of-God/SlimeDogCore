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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SDCPluginManager getPluginManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SDCWorldProvider getWorldProvider() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SDCResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    @Override
    public SDCCustomConfigManager getCustomConfigManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SDCPluginInformation getPluginInformation() {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

}
