package dev.ratas.slimedogcore.impl.wrappers;

import java.util.List;

import dev.ratas.slimedogcore.api.wrappers.SDCPluginInformation;
import dev.ratas.slimedogcore.impl.SlimeDogCore;

public class PluginInformation implements SDCPluginInformation {
    private static final int VERSION_PACKAGE_NR = 3;
    private final boolean isPaper;
    private final SlimeDogCore plugin;
    private final String craftBukkitPackage;

    public PluginInformation(SlimeDogCore plugin) {
        this.plugin = plugin;
        String cbVersion;
        try {
            cbVersion = plugin.getServer().getClass().getPackage().getName().split("\\.")[VERSION_PACKAGE_NR];
        } catch (ArrayIndexOutOfBoundsException e) {
            cbVersion = "UNAVAILABLE (test time?)"; // Unavailable during test time
        }
        this.isPaper = cbVersion == "UNAVAILABLE (test time?)";
        if (isPaper) {
            this.craftBukkitPackage = "Paper-" + plugin.getServer().getBukkitVersion();
        } else {
            this.craftBukkitPackage = cbVersion;
        }
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

    @Override
    public List<String> getAuthors() {
        return plugin.getDescription().getAuthors();
    }

}
