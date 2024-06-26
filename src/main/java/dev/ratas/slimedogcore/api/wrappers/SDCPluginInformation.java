package dev.ratas.slimedogcore.api.wrappers;

import java.util.List;

public interface SDCPluginInformation {

    /**
     * Get the version of the plugin.
     *
     * @return the plugin's version
     */
    String getPluginVersion();

    /**
     * Get the MineCraft version.
     *
     * @return the MC version
     */
    String getMCVersion();

    /**
     * Get the CraftBukkit package (e.g v1_19_R1)
     *
     * NB! This will not give the correct package path for Paper.
     *
     * @return the CraftBukkit package
     */
    String getCraftBukkitPackage();

    /**
     * Get the full CraftBukkit package.
     *
     * This should work for both Bukkit / Spigot and Paper.
     *
     * @return the full CraftBukkit package.
     */
    String getCraftBukkitFullPackage();

    /**
     * Get the name of the plugin.
     *
     * @return the name of the plugin
     */
    String getPluginName();

    /**
     * Get the authors of the plugin.
     *
     * @return the authors of the plugin
     */
    List<String> getAuthors();

}
