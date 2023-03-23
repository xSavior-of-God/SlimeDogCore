package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCTripleContextFactory;

/**
 * The triple message factory is used to construct a message with three required
 * inputs.
 */
public interface SDCTripleContextMessageFactory<T1, T2, T3> extends SDCMessageFactory<SDCTripleContext<T1, T2, T3>> {

    /**
     * Gets the context factory associated with this message factory.
     *
     * @return the associated context factory
     */
    SDCTripleContextFactory<T1, T2, T3> getContextFactory();

    /**
     * Creates the message with the specified context.
     *
     * @param context1 the first context
     * @param context2 the second context
     * @param context3 the third context
     * @return the corrseponding message
     */
    default SDCMessage<SDCTripleContext<T1, T2, T3>> createWith(T1 context1, T2 context2, T3 contect3) {
        return getMessage(getContextFactory().getContext(context1, context2, contect3));
    }

}
