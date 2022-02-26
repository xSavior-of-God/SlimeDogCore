package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

public interface SDCSingleContextMessage<T> extends SDCMessage<SDCSingleContext<T>> {

}
