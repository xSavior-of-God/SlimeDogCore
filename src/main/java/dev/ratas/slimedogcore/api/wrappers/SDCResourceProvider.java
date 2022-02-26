package dev.ratas.slimedogcore.api.wrappers;

import java.io.InputStream;

/**
 * This class helps get resources from the plugin jar and save them to file.
 */
public interface SDCResourceProvider {

    /**
     * Gets an embedded resource in the plugin
     *
     * @param filename the file name
     * @return the input
     */
    InputStream getResource(String filename);

    /**
     * Saves the raw contents of any resource embedded with a plugin's .jar file
     * assuming it can be found using getResource(String).
     *
     * The resource is saved into the plugin's data folder using the same hierarchy
     * as the .jar file (subdirectories are preserved).
     *
     * @param resourcePath the resource path
     * @param replace      whether or not to replace (potentially) existing file
     */
    void saveResource(String resourcePath, boolean replace);

}
