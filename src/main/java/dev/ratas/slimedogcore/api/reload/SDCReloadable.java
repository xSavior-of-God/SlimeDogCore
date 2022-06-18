package dev.ratas.slimedogcore.api.reload;

public interface SDCReloadable {

    /**
     * Reloads component.
     *
     * @throws ReloadException when there's an issue reloading the component
     */
    void reload() throws ReloadException;

}
