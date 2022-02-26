package dev.ratas.slimedogcore.api.messaging.context;

public interface SDCSingleContext<T> extends SDCContext {

    T getContents();

    default int getNumberOfPlaceholders() {
        return 1;
    }

}
