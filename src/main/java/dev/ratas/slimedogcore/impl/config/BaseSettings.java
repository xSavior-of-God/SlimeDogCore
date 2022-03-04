package dev.ratas.slimedogcore.impl.config;

import java.util.function.Supplier;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.settings.SDCBaseSettings;

public class BaseSettings implements SDCBaseSettings {
    private final Supplier<SDCCustomConfig> configSupplier;

    public BaseSettings(Supplier<SDCCustomConfig> configSupplier) {
        this.configSupplier = configSupplier;
    }

    @Override
    public boolean isDebugModeEnabled() {
        return configSupplier.get().getConfig().getBoolean("debug", false);
    }

}
