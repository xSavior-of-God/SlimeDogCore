package dev.ratas.slimedogcore.impl.messaging;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public class ContextMessage<T extends SDCContext> implements SDCMessage<T> {
    private final String raw;
    private final T context;
    private final MessageTarget msgTarget;

    public ContextMessage(String raw, T context, MessageTarget msgTarget) {
        this.raw = raw;
        this.context = context;
        this.msgTarget = msgTarget;
    }

    @Override
    public String getRaw() {
        return raw;
    }

    @Override
    public T context() {
        return context;
    }

    @Override
    public MessageTarget getTarget() {
        return msgTarget;
    }

    @Override
    public void sendTo(SDCRecipient recpient) {
        recpient.sendMessage(this);
    }

}
