package dev.ratas.slimedogcore.impl.messaging.context.delegating;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

public class DelegatingDoubleContext<T1, T2> implements SDCDoubleContext<T1, T2> {
    private final SDCSingleContext<T1> delegate1;
    private final SDCSingleContext<T2> delegate2;

    public DelegatingDoubleContext(SDCSingleContext<T1> delegate1, SDCSingleContext<T2> delegate2) {
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
        return delegate1.getContents();
    }

    @Override
    public T2 getContentsTwo() {
        return delegate2.getContents();
    }

}
