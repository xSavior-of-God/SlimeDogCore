package dev.ratas.slimedogcore.impl.messaging;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;

public class ContextMessage<T extends SDCContext> implements SDCMessage<T> {
    private final String raw;
    private final T context;
    private final MessageTarget msgTarget;
    private final SDCMessageDeliverer messageDeliverer;

    public ContextMessage(String raw, T context, MessageTarget msgTarget, SDCMessageDeliverer messageDeliverer) {
        this.raw = raw;
        this.context = context;
        this.msgTarget = msgTarget;
        this.messageDeliverer = messageDeliverer;
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
    public SDCMessageDeliverer getDeliverer() {
        return messageDeliverer;
    }

    @Override
    public void sendTo(CommandSender sender) {
        messageDeliverer.deliver(this, sender);
    }

}
