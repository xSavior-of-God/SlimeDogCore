package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;

/**
 * The double context factory is used to build contexts that depend on two
 * types of input.
 */
public interface SDCDoubleContextFactory<T1, T2> extends SDCContextFactory<SDCDoubleContext<T1, T2>> {

    /**
     * Gets the context based on the two types of input required.
     *
     * @param t1 input of type 1
     * @param t2 input of type 2
     * @return the resulting context
     */
    SDCDoubleContext<T1, T2> getContext(T1 t1, T2 t2);

}
