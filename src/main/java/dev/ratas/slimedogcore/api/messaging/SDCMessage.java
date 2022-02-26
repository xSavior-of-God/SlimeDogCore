package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
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
     * @param recipient the recipient
     */
    void sendTo(SDCRecipient recipient);

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

}
