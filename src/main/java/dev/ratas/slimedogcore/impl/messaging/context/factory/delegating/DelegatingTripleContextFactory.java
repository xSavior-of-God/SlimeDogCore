package dev.ratas.slimedogcore.impl.messaging.context.factory.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCTripleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingTripleContext;

public class DelegatingTripleContextFactory<T1, T2, T3> implements SDCTripleContextFactory<T1, T2, T3> {
    private final SDCSingleContextFactory<T1> delegate1;
    private final SDCSingleContextFactory<T2> delegate2;
    private final SDCSingleContextFactory<T3> delegate3;

    public DelegatingTripleContextFactory(SDCSingleContextFactory<T1> delegate1, SDCSingleContextFactory<T2> delegate2,
            SDCSingleContextFactory<T3> delegate3) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
        this.delegate3 = delegate3;
    }

    @Override
    public SDCTripleContext<T1, T2, T3> getContext(T1 t1, T2 t2, T3 t3) {
        return new DelegatingTripleContext<>(delegate1.getContext(t1), delegate2.getContext(t2),
                delegate3.getContext(t3));
    }

}
