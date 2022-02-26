package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SingleContext;

public interface SingleContextFactory<T> extends ContextFactory<SingleContext<T>> {

    SingleContext<T> getContext(T t);

}
