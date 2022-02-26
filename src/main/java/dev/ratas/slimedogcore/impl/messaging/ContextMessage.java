package dev.ratas.slimedogcore.impl.messaging;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;

public class ContextMessage<T extends SDCContext> implements SDCMessage<T> {
    private final String raw;
    private final T context;
    private final SDCMessageDeliverer messageDeliverer;

    public ContextMessage(String raw, T context, SDCMessageDeliverer messageDeliverer) {
        this.raw = raw;
        this.context = context;
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
        return messageDeliverer.getDeliveryTarget();
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
