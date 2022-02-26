package dev.ratas.slimedogcore.impl.messaging.context.factory.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCQuadrupleContextFactory;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingQuadrupleContext;

public class DelegatingQuadrupleContextFactory<T1, T2, T3, T4> implements SDCQuadrupleContextFactory<T1, T2, T3, T4> {
    private final SDCSingleContextFactory<T1> delegate1;
    private final SDCSingleContextFactory<T2> delegate2;
    private final SDCSingleContextFactory<T3> delegate3;
    private final SDCSingleContextFactory<T4> delegate4;

    public DelegatingQuadrupleContextFactory(SDCSingleContextFactory<T1> delegate1,
            SDCSingleContextFactory<T2> delegate2, SDCSingleContextFactory<T3> delegate3,
            SDCSingleContextFactory<T4> delegate4) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
        this.delegate3 = delegate3;
        this.delegate4 = delegate4;
    }

    @Override
    public SDCQuadrupleContext<T1, T2, T3, T4> getContext(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new DelegatingQuadrupleContext<>(delegate1.getContext(t1), delegate2.getContext(t2),
                delegate3.getContext(t3), delegate4.getContext(t4));
    }

}
