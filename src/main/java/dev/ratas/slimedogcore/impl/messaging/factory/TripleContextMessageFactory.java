package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCTripleContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.factory.SDCTripleContextMessageFactory;

public class TripleContextMessageFactory<T1, T2, T3> extends MessageFactory<SDCTripleContext<T1, T2, T3>>
        implements SDCTripleContextMessageFactory<T1, T2, T3> {

    public TripleContextMessageFactory(SDCTripleContextFactory<T1, T2, T3> contextFactory, String raw,
            MessageTarget msgTarget) {
        super(contextFactory, raw, msgTarget);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SDCTripleContextFactory<T1, T2, T3> getContextFactory() {
        return (SDCTripleContextFactory<T1, T2, T3>) super.getContextFactory();
    }

}
