package dev.ratas.slimedogcore.api.messaging.factory;

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

}
