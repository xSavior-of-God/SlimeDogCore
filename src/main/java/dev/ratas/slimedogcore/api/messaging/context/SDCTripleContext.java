package dev.ratas.slimedogcore.api.messaging.context;

public interface SDCTripleContext<T1, T2, T3> extends SDCContext {

    T1 getContentsOne();

    T2 getContentsTwo();

    T2 getContentsThree();

    default int getNumberOfPlaceholders() {
        return 3;
    }

}
