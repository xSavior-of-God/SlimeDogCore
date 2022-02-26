package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCDoubleContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.factory.SDCDoubleContextMessageFactory;

public class DoubleContextMessageFactory<T1, T2> extends MessageFactory<SDCDoubleContext<T1, T2>>
        implements SDCDoubleContextMessageFactory<T1, T2> {

    public DoubleContextMessageFactory(SDCDoubleContextFactory<T1, T2> contextFactory, String raw,
            MessageTarget msgTarget, SDCMessageDeliverer messageDeliverer) {
        super(contextFactory, raw, msgTarget, messageDeliverer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SDCDoubleContextFactory<T1, T2> getContextFactory() {
        return (SDCDoubleContextFactory<T1, T2>) super.getContextFactory();
    }

}
