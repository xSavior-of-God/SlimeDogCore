package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;

/**
 * The triple context message is one where three types of input is needed for
 * context and placeholder replacement.
 */
public interface SDCTripleContextMessage<T1, T2, T3> extends SDCMessage<SDCTripleContext<T1, T2, T3>> {

}
