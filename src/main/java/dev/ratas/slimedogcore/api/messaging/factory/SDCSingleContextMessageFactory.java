package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

/**
 * The single message factory is used to construct a message with one required
 * input.
 */
public interface SDCSingleContextMessageFactory<T> extends SDCMessageFactory<SDCSingleContext<T>> {

}
