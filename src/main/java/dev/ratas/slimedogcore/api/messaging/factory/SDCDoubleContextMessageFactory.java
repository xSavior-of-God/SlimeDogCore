package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCDoubleContextFactory;

/**
 * The double message factory is used to construct a message with two required
 * inputs.
 */
public interface SDCDoubleContextMessageFactory<T1, T2> extends SDCMessageFactory<SDCDoubleContext<T1, T2>> {

    /**
     * Gets the context factory associated with this message factory.
     *
     * @return the associated context factory
     */
    SDCDoubleContextFactory<T1, T2> getContextFactory();

    /**
     * Creates the message with the specified context.
     *
     * @param context1 the first context
     * @param context2 the second context
     * @return the corrseponding message
     */
    default SDCMessage<SDCDoubleContext<T1, T2>> createWith(T1 context1, T2 context2) {
        return getMessage(getContextFactory().getContext(context1, context2));
    }

}
