package dev.ratas.slimedogcore.impl.wrappers;

import dev.ratas.slimedogcore.api.wrappers.SDCPluginInformation;
import dev.ratas.slimedogcore.impl.SlimeDogCore;

public class PluginInformation implements SDCPluginInformation {
    private static final int VERSION_PACKAGE_NR = 3;
    private final SlimeDogCore plugin;
    private final String craftBukkitPackage;

    public PluginInformation(SlimeDogCore plugin) {
        this.plugin = plugin;
        this.craftBukkitPackage = plugin.getServer().getClass().getPackage().getName().split("\\.")[VERSION_PACKAGE_NR];
    }

    @Override
    public String getPluginVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String getMCVersion() {
        return plugin.getServer().getVersion();
    }

    @Override
    public String getCraftBukkitPackage() {
        return craftBukkitPackage;
    }

    @Override
    public String getPluginName() {
        return plugin.getName();
    }

}
