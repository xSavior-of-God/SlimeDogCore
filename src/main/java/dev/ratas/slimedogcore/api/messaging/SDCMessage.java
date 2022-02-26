package dev.ratas.slimedogcore.api.messaging;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;

public interface SDCMessage<T extends SDCContext> {

    String getRaw();

    void sendTo(CommandSender sender);

    T context();

    MessageTarget getTarget();

    SDCMessageDeliverer getDeliverer();

}
