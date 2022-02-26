package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SingleContext;

public interface SingleContextMessage<T> extends Message<SingleContext<T>> {

}
