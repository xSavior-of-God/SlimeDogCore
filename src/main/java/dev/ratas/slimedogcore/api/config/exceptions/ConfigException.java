package dev.ratas.slimedogcore.api.config.exceptions;

public abstract class ConfigException extends IllegalStateException {

    protected ConfigException(Throwable delegate) {
        super(delegate);
    }

    protected ConfigException(String msg) {
        super(msg);
    }

    protected ConfigException(String msg, Throwable delegate) {
        super(msg, delegate);
    }

}
