package dev.ratas.slimedogcore.api.config.exceptions;

public class ConfigReloadException extends ConfigException {

    public ConfigReloadException(Throwable delegate) {
        super(delegate);
    }

    public ConfigReloadException(String msg) {
        super(msg);
    }

    public ConfigReloadException(String msg, Throwable delegate) {
        super(msg, delegate);
    }

}
