package dev.ratas.slimedogcore.api.messaging.context;

public interface SDCDoubleContext<T1, T2> extends SDCContext {

    T1 getContentsOne();

    T2 getContentsTwo();

    default int getNumberOfPlaceholders() {
        return 2;
    }

}
