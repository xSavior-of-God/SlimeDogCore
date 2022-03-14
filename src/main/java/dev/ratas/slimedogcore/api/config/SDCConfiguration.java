package dev.ratas.slimedogcore.api.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A sort of wrapper for {@link org.bukkit.configuration.ConfigurationSection}
 * and/or {@link org.bukkit.configuration.file.FileConfiguration}.
 */
public interface SDCConfiguration {

    /**
     * Get all keys within the current section.
     *
     * @param deep whether to traverse the config tree
     * @return collection of keys
     */
    Collection<String> getKeys(boolean deep);

    /**
     * Get the key-value map within the current section.
     *
     * @param deep whether to travers the config tree
     * @return the map of keys and values
     */
    Map<String, Object> getValues(boolean deep);

    /**
     * Check if the path has been defined in config.
     *
     * @param path the path in question
     * @return true if the path was defined (or there is a default), false otherwise
     */
    boolean contains(String path);

    /**
     * Checks if the path has been defined in config. Allows for ignoring defaults.
     *
     * @param path          the path in question
     * @param ignoreDefault whether or not the default should be considered
     * @return true if the path was defined or the ignoreDefault is true and there
     *         was a default, false otherwise
     */
    boolean contains(String path, boolean ignoreDefault);

    /**
     * Check if path is set in config.
     *
     * @param path the path in question
     * @return whether the path has been set
     */
    boolean isSet(String path);

    /**
     * Gets the path of the current section.
     *
     * @return the current path
     */
    String getCurrentPath();

    /**
     * Gets the name of the current section.
     *
     * @return the section name
     */
    String getName();

    /**
     * Gets whatever is defined at the path or the default or null.
     *
     * @param path the path in question
     * @return the object stored at the path or the default (if present) or null
     */
    Object get(String path);

    /**
     * Gets whatever is defined at the path or a specified default.
     *
     * @param path the path in question
     * @param def  the default if nothing is set in config
     * @return the object at the path (if present) or the specified default
     */
    Object get(String path, Object def);

    /**
     * Gets the String specified at the the path or the default or null.
     * 
     * @param path the path in question
     * @return the String stored at the path or the default (if present) or null
     */
    String getString(String path);

    /**
     * Gets the String defined at the path or a specified default.
     *
     * @param path the path in question
     * @param def  the default if nothing is set in config
     * @return the String at the path (if present) or the specified default
     */
    String getString(String path, String def);

    /**
     * Gets the int specified at the path or the default or 0
     *
     * @param path the path in question
     * @return the int at the path (if present) or 0
     */
    int getInt(String path);

    /**
     * Gets the int defined at the path or a specified default.
     *
     * @param path the path in question
     * @param def  the default if nothing is set in config
     * @return the int at the path (if present) or the specified default
     */
    int getInt(String path, int def);

    /**
     * Gets the boolean specified at the path or the default or false
     *
     * @param path the path in question
     * @return the boolean at the path (if present) or false
     */
    boolean getBoolean(String path);

    /**
     * Gets the boolean defined at the path or a specified default.
     *
     * @param path the path in question
     * @param def  the default if nothing is set in config
     * @return the boolean at the path (if present) or the specified default
     */
    boolean getBoolean(String path, boolean def);

    /**
     * Gets the double specified at the path or the default or 0
     *
     * @param path the path in question
     * @return the double at the path (if present) or 0
     */
    double getDouble(String path);

    /**
     * Gets the double defined at the path or a specified default.
     *
     * @param path the path in question
     * @param def  the default if nothing is set in config
     * @return the double at the path (if present) or the specified default
     */
    double getDouble(String path, double def);

    /**
     * Gets the long specified at the path or the default or 0
     *
     * @param path the path in question
     * @return the long at the path (if present) or 0
     */
    long getLong(String path);

    /**
     * Gets the long defined at the path or a specified default.
     *
     * @param path the path in question
     * @param def  the default if nothing is set in config
     * @return the long at the path (if present) or the specified default
     */
    long getLong(String path, long def);

    /**
     * Gets the List of items specified at the path or the default or null
     *
     * @param path the path in question
     * @return the List of items at the path (if present) or null
     */
    List<?> getList(String path);

    /**
     * Gets the List of items defined at the path or a specified default.
     *
     * @param path the path in question
     * @param def  the default if nothing is set in config
     * @return the double at the path (if present) or the specified default
     */
    List<?> getList(String path, List<?> def);

    /**
     * Gets the List of Strings specified at the path or the default or an emtpy
     * list
     *
     * @param path the path in question
     * @return the List of items at the path (if present) or an emtpy list
     */
    List<String> getStringList(String path);

    /**
     * Gets the configuration at the specified path or null.
     *
     * @param path the path in question
     * @return the configuraiton (is present) or null
     */
    SDCConfiguration getConfigurationSection(String path);

    /**
     * Gets the default section defined (if it exists) or null.
     *
     * @return the default section if defined, else null
     */
    SDCConfiguration getDefaultSection();

}
