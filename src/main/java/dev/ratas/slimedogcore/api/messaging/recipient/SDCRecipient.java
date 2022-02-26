package dev.ratas.slimedogcore.api.messaging.recipient;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;

/**
 * The recipient of messages as well as a permission holder.
 */
public interface SDCRecipient {

    /**
     * Send a message to the recipient.
     *
     * @param <T>     the type of context for the message
     * @param message the message in question
     */
    <T extends SDCContext> void sendMessage(SDCMessage<T> message);

    /**
     * Send raw message to recipient.
     *
     * @param msg the message
     */
    void sendRawMessage(String msg);

    /**
     * Check whether the recipient has certain permissions.
     *
     * @param perms the permissions in question
     * @return whether or not the recpient has the permission
     */
    boolean hasPermission(String perms);

}
