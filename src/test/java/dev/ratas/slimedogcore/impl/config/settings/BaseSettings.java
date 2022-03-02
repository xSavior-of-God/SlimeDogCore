package dev.ratas.slimedogcore.impl.config.settings;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.settings.SDCBaseSettings;

public class BaseSettings implements SDCBaseSettings {
    private final SDCCustomConfig config;

    public BaseSettings(SDCCustomConfig config) {
        this.config = config;
    }

    @Override
    public boolean isDebugModeEnabled() {
        return config.getConfig().getBoolean("debug", false);
    }

}
