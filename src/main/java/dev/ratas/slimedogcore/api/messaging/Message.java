package dev.ratas.slimedogcore.api.messaging;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.context.Context;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;

public interface Message<T extends Context> {

    String getRaw();

    void sendTo(CommandSender sender);

    T context();

    MessageTarget getTarget();

    MessageDeliverer getDeliverer();

}
