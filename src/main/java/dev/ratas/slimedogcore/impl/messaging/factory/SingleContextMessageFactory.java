package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.factory.SDCSingleContextMessageFactory;

public class SingleContextMessageFactory<T> extends MessageFactory<SDCSingleContext<T>>
        implements SDCSingleContextMessageFactory<T> {

    public SingleContextMessageFactory(SDCSingleContextFactory<T> contextFactory, String raw, MessageTarget msgTarget,
            SDCMessageDeliverer messageDeliverer) {
        super(contextFactory, raw, msgTarget, messageDeliverer);
    }

    @Override
    public SDCSingleContextFactory<T> getContextFactory() {
        return (SDCSingleContextFactory<T>) super.getContextFactory();
    }

}
