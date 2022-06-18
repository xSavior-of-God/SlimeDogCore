package dev.ratas.slimedogcore.api.reload;

public interface SDCReloadManager {

    /**
     * Register a reloadable component with this reload manager.
     *
     * @param reloadable the reloadable component to be registered
     */
    void register(SDCReloadable reloadable);

    /**
     * Reload all registered reloadable components.
     *
     * @throws ReloadException if there was an issue reloading the components
     */
    void reload() throws ReloadException;

}
