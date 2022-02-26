package dev.ratas.slimedogcore.api.messaging.context;

public interface VoidContext extends Context {

    default int getNumberOfPlaceholders() {
        return 0;
    }

}
