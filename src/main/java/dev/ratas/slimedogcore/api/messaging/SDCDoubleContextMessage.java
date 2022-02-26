package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;

/**
 * The double context message is one where two types of input is needed for
 * context and placeholder replacement.
 */
public interface SDCDoubleContextMessage<T1, T2> extends SDCMessage<SDCDoubleContext<T1, T2>> {

}
