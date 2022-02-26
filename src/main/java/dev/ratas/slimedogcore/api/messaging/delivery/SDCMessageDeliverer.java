package dev.ratas.slimedogcore.api.messaging.delivery;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;

/**
 * Handles message delivery.
 */
public interface SDCMessageDeliverer {

    /**
     * Delivers the message to a command sender.
     *
     * @param <T>     the type of context that is used for the message
     * @param message the message
     * @param sender  the command sender and message recipient
     */
    <T extends SDCContext> void deliver(SDCMessage<T> message, CommandSender sender);

    /**
     * Gets the delivery target type.
     *
     * @return the type of delivery target
     */
    MessageTarget getDeliveryTarget();

}
