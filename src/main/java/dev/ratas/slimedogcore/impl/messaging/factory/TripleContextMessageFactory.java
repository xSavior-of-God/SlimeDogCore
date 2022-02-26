package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCTripleContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.factory.SDCTripleContextMessageFactory;

public class TripleContextMessageFactory<T1, T2, T3> extends MessageFactory<SDCTripleContext<T1, T2, T3>>
        implements SDCTripleContextMessageFactory<T1, T2, T3> {

    public TripleContextMessageFactory(SDCTripleContextFactory<T1, T2, T3> contextFactory, String raw,
            SDCMessageDeliverer messageDeliverer) {
        super(contextFactory, raw, messageDeliverer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SDCTripleContextFactory<T1, T2, T3> getContextFactory() {
        return (SDCTripleContextFactory<T1, T2, T3>) super.getContextFactory();
    }

}
