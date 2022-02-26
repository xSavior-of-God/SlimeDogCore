package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

/**
 * The single context message is one where one type of input is needed for
 * context and placeholder replacement.
 */
public interface SDCSingleContextMessage<T> extends SDCMessage<SDCSingleContext<T>> {

}
