package dev.ratas.slimedogcore.api.messaging.context;

public interface SDCVoidContext extends SDCContext {

    default int getNumberOfPlaceholders() {
        return 0;
    }

}
