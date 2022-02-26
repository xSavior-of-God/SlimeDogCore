package dev.ratas.slimedogcore.api.messaging.factory;

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

}
