package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCQuadrupleContextFactory;

/**
 * The quadruple message factory is used to construct a message with four
 * required inputs.
 */
public interface SDCQuadrupleContextMessageFactory<T1, T2, T3, T4>
        extends SDCMessageFactory<SDCQuadrupleContext<T1, T2, T3, T4>> {

    /**
     * Gets the context factory associated with this message factory.
     *
     * @return the associated context factory
     */
    SDCQuadrupleContextFactory<T1, T2, T3, T4> getContextFactory();

    /**
     * Creates the message with the specified context.
     *
     * @param context1 the first context
     * @param context2 the second context
     * @param context3 the third context
     * @param context4 the fourth context
     * @return the corrseponding message
     */
    default SDCMessage<SDCQuadrupleContext<T1, T2, T3, T4>> createWith(T1 context1, T2 context2, T3 contect3,
            T4 context4) {
        return getMessage(getContextFactory().getContext(context1, context2, contect3, context4));
    }

}
