package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;

/**
 * The double message factory is used to construct a message with two required
 * inputs.
 */
public interface SDCDoubleContextMessageFactory<T1, T2> extends SDCMessageFactory<SDCDoubleContext<T1, T2>> {

}
