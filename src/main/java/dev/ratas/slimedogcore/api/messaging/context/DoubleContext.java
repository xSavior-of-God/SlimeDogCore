package dev.ratas.slimedogcore.api.messaging.context;

public interface DoubleContext<T1, T2> extends Context {

    T1 getContentsOne();

    T2 getContentsTwo();

    default int getNumberOfPlaceholders() {
        return 2;
    }

}
