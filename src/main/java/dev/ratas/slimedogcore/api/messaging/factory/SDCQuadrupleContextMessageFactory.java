package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;

/**
 * The quadruple message factory is used to construct a message with four
 * required inputs.
 */
public interface SDCQuadrupleContextMessageFactory<T1, T2, T3, T4>
                extends SDCMessageFactory<SDCQuadrupleContext<T1, T2, T3, T3>> {

}
