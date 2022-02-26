package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.impl.messaging.ContextMessage;

public class MessageFactory<T extends SDCContext> extends AbstractMessageFactory<T> {
    private final String raw;
    private final MessageTarget msgTarget;

    public MessageFactory(SDCContextFactory<T> contextFactory, String raw, MessageTarget msgTarget) {
        super(contextFactory);
        this.raw = raw;
        this.msgTarget = msgTarget;
    }

    @Override
    public SDCMessage<T> getMessage(T context) {
        return new ContextMessage<>(raw, context, msgTarget);
    }

}
