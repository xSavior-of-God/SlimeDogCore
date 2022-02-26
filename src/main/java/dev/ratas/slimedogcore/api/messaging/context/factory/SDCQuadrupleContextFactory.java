package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;

/**
 * The quadruple context factory is used to build contexts that depend on four
 * types of input.
 */
public interface SDCQuadrupleContextFactory<T1, T2, T3, T4>
        extends SDCContextFactory<SDCQuadrupleContext<T1, T2, T3, T4>> {

    /**
     * Gets the context based on the four types of input required.
     *
     * @param t1 input of type 1
     * @param t2 input of type 2
     * @param t3 input of type 3
     * @param t4 input of type 4
     * @return the resulting context
     */
    SDCQuadrupleContext<T1, T2, T3, T4> getContext(T1 t1, T2 t2, T3 t3, T4 t4);

}
