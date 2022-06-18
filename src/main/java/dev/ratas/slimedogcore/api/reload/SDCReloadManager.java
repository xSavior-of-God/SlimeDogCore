package dev.ratas.slimedogcore.api.reload;

public interface SDCReloadManager {

    /**
     * Register a reloadable component with this reload manager.
     * This operation will fail if the component has already been registered.
     *
     * @param reloadable the reloadable component to be registered
     * @throws IllegalStateException if the component has already been registered
     */
    void register(SDCReloadable reloadable) throws IllegalStateException;

    /**
     * Reload all registered reloadable components.
     *
     * @throws ReloadException if there was an issue reloading the components
     */
    void reload() throws ReloadException;

}
