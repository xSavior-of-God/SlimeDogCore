package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;

/**
 * The message factory is used to construct a message.
 */
public interface SDCMessageFactory<C extends SDCContext> {

    /**
     * Gets the context factory associated with this message factory.
     *
     * @return the associated context factory
     */
    SDCContextFactory<C> getContextFactory();

    /**
     * Generates the message instance based on the context specified.
     *
     * @param context the specified context
     * @return the corrseponding message
     */
    SDCMessage<C> getMessage(C context);
}
