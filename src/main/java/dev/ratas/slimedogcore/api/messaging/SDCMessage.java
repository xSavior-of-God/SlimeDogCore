package dev.ratas.slimedogcore.api.messaging;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;

/**
 * Describes all the information used to determine what and how to send to a
 * recipient.
 */
public interface SDCMessage<T extends SDCContext> {

    /**
     * Gets the raw/unmodified string as it was read (likely from a config).
     *
     * @return the raw message string
     */
    String getRaw();

    /**
     * Sends the message to the recipient as per the message target specified.
     *
     * @param sender the recipient
     */
    void sendTo(CommandSender sender);

    /**
     * Gets the context that will be used to fill in the placeholder(s) in the raw
     * message.
     *
     * @return the context associated with this message
     */
    T context();

    /**
     * Gets the target of this message.
     *
     * @return the message target
     */
    MessageTarget getTarget();

    /**
     * Gets the message deliverer that us used with this message
     *
     * @return the message deliverer
     */
    SDCMessageDeliverer getDeliverer();

}
