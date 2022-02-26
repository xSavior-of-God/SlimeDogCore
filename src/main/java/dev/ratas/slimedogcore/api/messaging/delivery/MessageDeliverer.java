package dev.ratas.slimedogcore.api.messaging.delivery;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.Message;
import dev.ratas.slimedogcore.api.messaging.context.Context;

public interface MessageDeliverer {

    <T extends Context> void deliver(Message<T> message, CommandSender sender);

    MessageTarget getDeliveryTarget();

}
