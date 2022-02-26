package dev.ratas.slimedogcore.impl.messaging.context.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;

public class DelegatingTripleContext<T1, T2, T3> implements SDCTripleContext<T1, T2, T3> {
    private final SDCSingleContext<T1> delegate1;
    private final SDCSingleContext<T2> delegate2;
    private final SDCSingleContext<T3> delegate3;

    public DelegatingTripleContext(SDCSingleContext<T1> delegate1, SDCSingleContext<T2> delegate2,
            SDCSingleContext<T3> delegate3) {
        this.delegate1 = delegate1;
        this.delegate2 = delegate2;
        this.delegate3 = delegate3;
    }

    @Override
    public String fill(String msg) {
        msg = delegate1.fill(msg);
        msg = delegate2.fill(msg);
        msg = delegate3.fill(msg);
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

}
