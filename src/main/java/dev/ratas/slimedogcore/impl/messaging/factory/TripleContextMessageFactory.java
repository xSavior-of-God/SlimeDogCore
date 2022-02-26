package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCTripleContextMessageFactory;

public class TripleContextMessageFactory<T1, T2, T3> extends MessageFactory<SDCTripleContext<T1, T2, T3>>
        implements SDCTripleContextMessageFactory<T1, T2, T3> {

    public TripleContextMessageFactory(Class<?> messageClass, Class<?> contextClass,
            SDCContextFactory<SDCTripleContext<T1, T2, T3>> contextFactory, String raw) {
        super(messageClass, contextClass, contextFactory, raw);
    }

    @Override
    public SDCContextFactory<SDCTripleContext<T1, T2, T3>> getContextFactory() {
        return (SDCContextFactory<SDCTripleContext<T1, T2, T3>>) super.getContextFactory();
    }

}
