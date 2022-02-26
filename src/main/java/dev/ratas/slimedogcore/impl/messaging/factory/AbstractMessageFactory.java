package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.factory.SDCMessageFactory;

public abstract class AbstractMessageFactory<T extends SDCContext> implements SDCMessageFactory<T> {
    protected final SDCContextFactory<T> contextFactory;
    protected final MessageTarget msgTarget;
    protected final SDCMessageDeliverer messageDeliverer;

    public AbstractMessageFactory(SDCContextFactory<T> contextFactory, MessageTarget msgTarget,
            SDCMessageDeliverer messageDeliverer) {
        this.contextFactory = contextFactory;
        this.msgTarget = msgTarget;
        this.messageDeliverer = messageDeliverer;
    }

    @Override
    public SDCContextFactory<T> getContextFactory() {
        return contextFactory;
    }

}
