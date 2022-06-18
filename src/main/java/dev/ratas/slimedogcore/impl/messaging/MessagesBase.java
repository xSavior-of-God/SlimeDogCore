package dev.ratas.slimedogcore.impl.messaging;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.reload.ReloadException;
import dev.ratas.slimedogcore.api.reload.SDCReloadable;

public abstract class MessagesBase implements SDCReloadable {
    private final SDCCustomConfig config;

    protected MessagesBase(SDCCustomConfig config) {
        this.config = config;
        config.saveDefaultConfig();
    }

    protected SDCCustomConfig getConfig() {
        return config;
    }

    protected String getRawMessage(String path, String def) {
        return config.getConfig().getString(path, def);
    }

    public void reloadConfig() {
        config.reloadConfig();
    }

    protected MessageSection getSubsection(String path) {
        return new MessageSection(config.getConfig().getConfigurationSection(path));
    }

    public boolean isEmpty() {
        return config.getConfig().getKeys(true).isEmpty();
    }

    @Override
    public void reload() throws ReloadException {
        config.reload();
    }

}
