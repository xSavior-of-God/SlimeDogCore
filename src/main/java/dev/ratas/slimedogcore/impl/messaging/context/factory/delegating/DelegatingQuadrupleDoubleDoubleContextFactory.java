package dev.ratas.slimedogcore.impl.messaging.context.factory.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCDoubleContextFactory;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCQuadrupleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingQuadrupleDoubleDoubleContext;

public class DelegatingQuadrupleDoubleDoubleContextFactory<T1, T2, T3, T4>
        implements SDCQuadrupleContextFactory<T1, T2, T3, T4> {
    private final SDCDoubleContextFactory<T1, T2> delegate1;
    private final SDCDoubleContextFactory<T3, T4> delegate2;

    public DelegatingQuadrupleDoubleDoubleContextFactory(SDCDoubleContextFactory<T1, T2> delegate1,
            SDCDoubleContextFactory<T3, T4> delegate2) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
    }

    @Override
    public SDCQuadrupleContext<T1, T2, T3, T4> getContext(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new DelegatingQuadrupleDoubleDoubleContext<>(delegate1.getContext(t1, t2), delegate2.getContext(t3, t4));
    }

}
