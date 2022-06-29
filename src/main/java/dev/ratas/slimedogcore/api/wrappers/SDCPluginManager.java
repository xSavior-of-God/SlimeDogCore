package dev.ratas.slimedogcore.api.wrappers;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This serves as a sort of wrapper for {@link org.bukkit.plugin.PluginManager}
 */
public interface SDCPluginManager {

    /**
     * Allows registering event listeners.
     *
     * @param listener the listener to be registered
     */
    void registerEvents(Listener listener);

    /**
     * Calls an event.
     *
     * @param event the event to be called
     */
    void callEvent(Event event);

    /**
     * Allows looking for plugins by name.
     *
     * @param name the name of the target plugin
     * @return the plugin (if found) or null
     */
    JavaPlugin getPluginByName(String name);

    default boolean isPluginEnabled(String name) {
        JavaPlugin plugin = getPluginByName(name);
        if (plugin == null) {
            return false;
        }
        return plugin.isEnabled();
    }

    /**
     * Attempts to get a plugin by its class.
     *
     * @param <T>   the target type
     * @param clazz the class of the target plugin
     * @return the plugin (if found) or null
     * @throws IllegalArgumentException if clazz is null
     * @throws IllegalArgumentException if clazz does not extend {@link JavaPlugin}
     * @throws IllegalStateException    if clazz was not provided by a plugin, for
     *                                  example, if called with
     *                                  <code>JavaPlugin.getPlugin(JavaPlugin.class)</code>
     * @throws IllegalStateException    if called from the static initializer for
     *                                  given JavaPlugin
     * @throws ClassCastException       if plugin that provided the class does not
     *                                  extend the class
     */
    <T extends JavaPlugin> T getPluginByClass(Class<T> clazz);

}
