package dev.ratas.slimedogcore.impl.messaging.context.factory.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCDoubleContextFactory;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingDoubleContext;

public class DelegatingDoubleContextFactory<T1, T2> implements SDCDoubleContextFactory<T1, T2> {
    private final SDCSingleContextFactory<T1> delegate1;
    private final SDCSingleContextFactory<T2> delegate2;

    public DelegatingDoubleContextFactory(SDCSingleContextFactory<T1> delegate1,
            SDCSingleContextFactory<T2> delegate2) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
    }

    @Override
    public SDCDoubleContext<T1, T2> getContext(T1 t1, T2 t2) {
        return new DelegatingDoubleContext<>(delegate1.getContext(t1), delegate2.getContext(t2));
    }

}
