package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

public interface SDCSingleContextFactory<T> extends SDCContextFactory<SDCSingleContext<T>> {

    SDCSingleContext<T> getContext(T t);

}
