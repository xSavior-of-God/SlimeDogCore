package dev.ratas.slimedogcore.api;

import java.io.File;
import java.util.logging.Logger;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;
import dev.ratas.slimedogcore.api.scheduler.SDCScheduler;
import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginInformation;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginManager;
import dev.ratas.slimedogcore.api.wrappers.SDCWorldProvider;

/**
 * Common interface on which all future SlimeDog plugins should be based.
 */
public interface SlimeDogPlugin {

    /**
     * Get the plugin's data folder (plugins/<PluginName>/)
     *
     * @return the plugin data folder
     */
    File getDataFolder();

    /**
     * Get the server world folder. This may be different from the default in some
     * setups.
     *
     * @return the world folder
     */
    File getWorldFolder();

    /**
     * Gets the scheduler.
     *
     * @return the scheduler
     */
    SDCScheduler getScheduler();

    /**
     * Gets the plugin manager.
     *
     * @return the plugin maanger
     */
    SDCPluginManager getPluginManager();

    /**
     * Gets the world provider.
     *
     * @return the world provider
     */
    SDCWorldProvider getWorldProvider();

    /**
     * Gets the resource provider.
     *
     * @return the resource provider
     */
    SDCResourceProvider getResourceProvider();

    /**
     * Gets the custom config handler.
     *
     * @return the custom config handler
     */
    SDCCustomConfigManager getCustomConfigManager();

    /**
     * Gets the plugin information.
     *
     * @return the plugin information
     */
    SDCPluginInformation getPluginInformation();

    /**
     * Called at the end of the plugin being enabled.
     */
    void pluginEnabled();

    /**
     * Called at the end of the plugin being disabled.
     */
    void pluginDisabled();

    /**
     * Gets the logger associated with the plugin
     *
     * @return the plugin logger
     */
    Logger getLogger();

    /**
     * Get the debug loggers. This logger is disabled if debug mode is off.
     *
     * @return the debug logger
     */
    SDCDebugLogger getDebugLogger();

    /**
     * Gets the default config (config.yml) of the plugin.
     *
     * @return the config.yml based config
     */
    default SDCCustomConfig getDefaultConfig() {
        return getCustomConfigManager().getDefaultConfig();
    }

}
