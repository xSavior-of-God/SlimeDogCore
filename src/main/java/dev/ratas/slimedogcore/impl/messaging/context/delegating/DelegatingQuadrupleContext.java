package dev.ratas.slimedogcore.impl.messaging.context.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

public class DelegatingQuadrupleContext<T1, T2, T3, T4> implements SDCQuadrupleContext<T1, T2, T3, T4> {
    private final SDCSingleContext<T1> delegate1;
    private final SDCSingleContext<T2> delegate2;
    private final SDCSingleContext<T3> delegate3;
    private final SDCSingleContext<T4> delegate4;

    public DelegatingQuadrupleContext(SDCSingleContext<T1> delegate1, SDCSingleContext<T2> delegate2,
            SDCSingleContext<T3> delegate3, SDCSingleContext<T4> delegate4) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
        this.delegate3 = delegate3;
        this.delegate4 = delegate4;
    }

    @Override
    public String fill(String msg) {
        msg = delegate1.fill(msg);
        msg = delegate2.fill(msg);
        msg = delegate3.fill(msg);
        msg = delegate4.fill(msg);
        return msg;
    }

    @Override
    public T1 getContentsOne() {
        return delegate1.getContents();
    }

    @Override
    public T2 getContentsTwo() {
        return delegate2.getContents();
    }

    @Override
    public T3 getContentsThree() {
        return delegate3.getContents();
    }

    @Override
    public T4 getContentsFour() {
        return delegate4.getContents();
    }

}
