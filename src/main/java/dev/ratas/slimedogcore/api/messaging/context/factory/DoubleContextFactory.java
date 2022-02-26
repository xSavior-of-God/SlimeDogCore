package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.DoubleContext;

public interface DoubleContextFactory<T1, T2> extends ContextFactory<DoubleContext<T1, T2>> {

    DoubleContext<T1, T2> getContext(T1 t1, T2 t2);

}
