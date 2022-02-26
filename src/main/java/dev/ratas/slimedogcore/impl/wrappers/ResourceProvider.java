package dev.ratas.slimedogcore.impl.wrappers;

import java.io.InputStream;

import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;
import dev.ratas.slimedogcore.impl.SlimeDogCore;

public class ResourceProvider implements SDCResourceProvider {
    private final SlimeDogCore plugin;

    public ResourceProvider(SlimeDogCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public InputStream getResource(String filename) {
        return plugin.getResource(filename);
    }

    @Override
    public void saveResource(String resourcePath, boolean replace) {
        plugin.saveResource(resourcePath, replace);
    }

}
