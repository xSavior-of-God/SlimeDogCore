package dev.ratas.slimedogcore.api.reload;

public class ReloadException extends IllegalStateException {

    public ReloadException(SDCReloadable component, String message) {
        super("Problem reloading component " + component + ": " + message);
    }

}
