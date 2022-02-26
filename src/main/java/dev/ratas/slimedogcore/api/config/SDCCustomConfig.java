package dev.ratas.slimedogcore.api.config;

import java.io.File;

import dev.ratas.slimedogcore.api.config.exceptions.ConfigReloadException;
import dev.ratas.slimedogcore.api.config.exceptions.ConfigSaveException;

/**
 * A custom config represented by a file.
 */
public interface SDCCustomConfig {

    /**
     * Reloads the contents of the config.
     *
     * @throws ConfigReloadException if the reload fails for one reason or another
     */
    void reloadConfig() throws ConfigReloadException;

    /**
     * Gets the file this config is referiring to.
     *
     * @return the file the config is read from
     */
    File getFile();

    /**
     * Get the configuration loaded from the file.
     *
     * @return the configuration
     */
    SDCConfiguration getConfig();

    /**
     * Save the current state of the config to file.
     *
     * @throws ConfigReloadException if the save fails for one reason or another
     */
    void saveConfig() throws ConfigSaveException;

    /**
     * Save the default config (from the jar) on file.
     *
     * @throws ConfigSaveException if the save fails for one reason or anotheru
     */
    void saveDefaultConfig() throws ConfigSaveException;

}
