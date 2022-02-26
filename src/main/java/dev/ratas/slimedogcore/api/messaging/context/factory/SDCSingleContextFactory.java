package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

/**
 * The single context factory is used to build contexts that depend on one
 * type of input.
 */
public interface SDCSingleContextFactory<T> extends SDCContextFactory<SDCSingleContext<T>> {

    /**
     * Gets the context based on the input required.
     *
     * @param t input
     * @return the resulting context
     */
    SDCSingleContext<T> getContext(T t);

}
