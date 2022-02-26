package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.TripleContext;

public interface TripleContextFactory<T1, T2, T3> extends ContextFactory<TripleContext<T1, T2, T3>> {

    TripleContext<T1, T2, T3> getContext(T1 t1, T2 t2, T3 t3);

}
