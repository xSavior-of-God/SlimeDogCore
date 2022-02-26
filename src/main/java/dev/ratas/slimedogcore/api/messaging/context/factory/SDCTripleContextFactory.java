package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;

/**
 * The triple context factory is used to build contexts that depend on three
 * types of input.
 */
public interface SDCTripleContextFactory<T1, T2, T3> extends SDCContextFactory<SDCTripleContext<T1, T2, T3>> {

    /**
     * Gets the context based on the three types of input required.
     *
     * @param t1 input of type 1
     * @param t2 input of type 2
     * @param t3 input of type 3
     * @return the resulting context
     */
    SDCTripleContext<T1, T2, T3> getContext(T1 t1, T2 t2, T3 t3);

}
