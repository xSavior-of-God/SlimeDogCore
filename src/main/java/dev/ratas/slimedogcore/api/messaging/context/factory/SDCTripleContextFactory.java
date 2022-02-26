package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;

public interface SDCTripleContextFactory<T1, T2, T3> extends SDCContextFactory<SDCTripleContext<T1, T2, T3>> {

    SDCTripleContext<T1, T2, T3> getContext(T1 t1, T2 t2, T3 t3);

}
