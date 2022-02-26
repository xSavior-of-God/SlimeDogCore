package dev.ratas.slimedogcore.impl.messaging.context.factory.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCDoubleContextFactory;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCTripleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingTripleLopsidedContext;

public class DelegatingTipleLopsidedContextFactory<T1, T2, T3> implements SDCTripleContextFactory<T1, T2, T3> {
    private final SDCDoubleContextFactory<T1, T2> delegate1;
    private final SDCSingleContextFactory<T3> delegate2;

    public DelegatingTipleLopsidedContextFactory(SDCDoubleContextFactory<T1, T2> delegate1,
            SDCSingleContextFactory<T3> delegate2) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
    }

    @Override
    public SDCTripleContext<T1, T2, T3> getContext(T1 t1, T2 t2, T3 t3) {
        return new DelegatingTripleLopsidedContext<>(delegate1.getContext(t1, t2), delegate2.getContext(t3));
    }

}
