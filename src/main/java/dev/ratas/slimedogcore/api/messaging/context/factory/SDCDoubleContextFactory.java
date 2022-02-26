package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;

public interface SDCDoubleContextFactory<T1, T2> extends SDCContextFactory<SDCDoubleContext<T1, T2>> {

    SDCDoubleContext<T1, T2> getContext(T1 t1, T2 t2);

}
