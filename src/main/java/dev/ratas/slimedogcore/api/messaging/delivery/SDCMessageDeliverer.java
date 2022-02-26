package dev.ratas.slimedogcore.api.messaging.delivery;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;

public interface SDCMessageDeliverer {

    <T extends SDCContext> void deliver(SDCMessage<T> message, CommandSender sender);

    MessageTarget getDeliveryTarget();

}
