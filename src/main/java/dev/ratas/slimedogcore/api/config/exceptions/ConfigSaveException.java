package dev.ratas.slimedogcore.api.config.exceptions;

public class ConfigSaveException extends ConfigException {

    public ConfigSaveException(Throwable delegate) {
        super(delegate);
    }

    public ConfigSaveException(String msg) {
        super(msg);
    }

    public ConfigSaveException(String msg, Throwable delegate) {
        super(msg, delegate);
    }

}
