package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;

/**
 * The triple message factory is used to construct a message with three required
 * inputs.
 */
public interface SDCTripleContextMessageFactory<T1, T2, T3> extends SDCMessageFactory<SDCTripleContext<T1, T2, T3>> {

}
