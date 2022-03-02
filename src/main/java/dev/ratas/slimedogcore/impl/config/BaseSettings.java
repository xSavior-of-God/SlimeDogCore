package dev.ratas.slimedogcore.impl.config;

import java.util.function.Supplier;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.settings.SDCBaseSettings;

public class BaseSettings implements SDCBaseSettings {
    private final Supplier<SDCCustomConfig> configSupplier;
    private SDCCustomConfig config = null;

    public BaseSettings(Supplier<SDCCustomConfig> configSupplier) {
        this.configSupplier = configSupplier;
    }

    @Override
    public boolean isDebugModeEnabled() {
        if (config == null) {
            config = configSupplier.get();
        }
        return config.getConfig().getBoolean("debug", false);
    }

}
