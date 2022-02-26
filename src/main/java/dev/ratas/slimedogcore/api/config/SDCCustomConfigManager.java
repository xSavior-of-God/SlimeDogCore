package dev.ratas.slimedogcore.api.config;

import java.io.File;

/**
 * This interfaces defined ways of obtaining custom config files without the
 * need to initialize them.
 */
public interface SDCCustomConfigManager {

    /**
     * Gets the config in the plugin data folder given the specified file name.
     *
     * @param fileName the file name
     * @return the custom config
     */
    SDCCustomConfig getConfig(String fileName);

    /**
     * Gets the config given the specified file.
     * 
     * If the file is not in the plugin data folder, an exception is thrown
     * 
     * @param file
     * @throws IllegalArgumentException if the file is not in the plugin data folder
     * @return
     */
    SDCCustomConfig getConfig(File file);

}
