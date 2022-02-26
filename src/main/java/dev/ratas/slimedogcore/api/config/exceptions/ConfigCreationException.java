package dev.ratas.slimedogcore.api.config.exceptions;

public class ConfigCreationException extends ConfigException {

    public ConfigCreationException(Throwable delegate) {
        super(delegate);
    }

    public ConfigCreationException(String msg) {
        super(msg);
    }

    public ConfigCreationException(String msg, Throwable delegate) {
        super(msg, delegate);
    }

}
