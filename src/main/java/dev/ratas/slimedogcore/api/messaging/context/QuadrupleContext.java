package dev.ratas.slimedogcore.api.messaging.context;

public interface QuadrupleContext<T1, T2, T3, T4> extends Context {

    T1 getContentsOne();

    T2 getContentsTwo();

    T3 getContentsThree();

    T4 getContentsFour();

    default int getNumberOfPlaceholders() {
        return 4;
    }

}
