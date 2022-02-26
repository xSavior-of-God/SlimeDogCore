package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;

/**
 * The quadruple context message is one where four types of input is needed for
 * context and placeholder replacement.
 */
public interface SDCQuadrupleContextMessage<T1, T2, T3, T4> extends SDCMessage<SDCQuadrupleContext<T1, T2, T3, T4>> {

}
