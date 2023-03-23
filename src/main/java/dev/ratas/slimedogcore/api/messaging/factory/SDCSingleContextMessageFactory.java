package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;

/**
 * The single message factory is used to construct a message with one required
 * input.
 */
public interface SDCSingleContextMessageFactory<T> extends SDCMessageFactory<SDCSingleContext<T>> {

    /**
     * Gets the context factory associated with this message factory.
     *
     * @return the associated context factory
     */
    SDCSingleContextFactory<T> getContextFactory();

    /**
     * Creates the message with the specified context.
     *
     * @param context the specified context
     * @return the corrseponding message
     */
    default SDCMessage<SDCSingleContext<T>> createWith(T context) {
        return getMessage(getContextFactory().getContext(context));
    }

}
