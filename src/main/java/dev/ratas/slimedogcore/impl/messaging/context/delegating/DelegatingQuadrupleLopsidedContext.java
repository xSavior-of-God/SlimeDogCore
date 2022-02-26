package dev.ratas.slimedogcore.impl.messaging.context.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;

public class DelegatingQuadrupleLopsidedContext<T1, T2, T3, T4> implements SDCQuadrupleContext<T1, T2, T3, T4> {
    private final SDCTripleContext<T1, T2, T3> delegate1;
    private final SDCSingleContext<T4> delegate2;

    public DelegatingQuadrupleLopsidedContext(SDCTripleContext<T1, T2, T3> delegate1,
            SDCSingleContext<T4> delegate2) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
    }

    @Override
    public String fill(String msg) {
        msg = delegate1.fill(msg);
        msg = delegate2.fill(msg);
        return msg;
    }

    @Override
    public T1 getContentsOne() {
        return delegate1.getContentsOne();
    }

    @Override
    public T2 getContentsTwo() {
        return delegate1.getContentsTwo();
    }

    @Override
    public T3 getContentsThree() {
        return delegate1.getContentsThree();
    }

    @Override
    public T4 getContentsFour() {
        return delegate2.getContents();
    }

}
