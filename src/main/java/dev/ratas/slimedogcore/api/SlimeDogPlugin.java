package dev.ratas.slimedogcore.api;

import java.io.File;

import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;
import dev.ratas.slimedogcore.api.scheduler.SDCScheduler;
import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginManager;
import dev.ratas.slimedogcore.api.wrappers.SDCWorldProvider;

public interface SlimeDogPlugin {

    File getDataFolder();

    File getWorldFolder();

    SDCScheduler getScheduler();

    SDCPluginManager getPluginManager();

    SDCWorldProvider getWorldProvider();

    SDCResourceProvider getResourceProvider();

    SDCCustomConfigManager getCustomConfigManager();

    void pluginEnabled();

    void pluginDisabled();

}
