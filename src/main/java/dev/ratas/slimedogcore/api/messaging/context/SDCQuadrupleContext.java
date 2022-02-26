package dev.ratas.slimedogcore.api.messaging.context;

public interface SDCQuadrupleContext<T1, T2, T3, T4> extends SDCContext {

    T1 getContentsOne();

    T2 getContentsTwo();

    T3 getContentsThree();

    T4 getContentsFour();

    default int getNumberOfPlaceholders() {
        return 4;
    }

}
