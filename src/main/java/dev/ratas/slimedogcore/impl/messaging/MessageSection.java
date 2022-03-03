package dev.ratas.slimedogcore.impl.messaging;

import dev.ratas.slimedogcore.api.config.SDCConfiguration;

public class MessageSection {
    private SDCConfiguration config;

    protected MessageSection(SDCConfiguration config) {
        this.config = config;
    }

    protected SDCConfiguration getConfig() {
        return config;
    }

    protected String getRawMessage(String path, String def) {
        return config.getString(path, def);
    }

}
