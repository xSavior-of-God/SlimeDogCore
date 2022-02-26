package dev.ratas.slimedogcore.api.messaging.context;

public interface SingleContext<T> extends Context {

    T getContents();

    default int getNumberOfPlaceholders() {
        return 1;
    }

}
