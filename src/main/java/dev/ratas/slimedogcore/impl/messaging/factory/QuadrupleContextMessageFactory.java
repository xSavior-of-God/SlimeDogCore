package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCQuadrupleContextMessageFactory;

public class QuadrupleContextMessageFactory<T1, T2, T3, T4> extends MessageFactory<SDCQuadrupleContext<T1, T2, T3, T4>>
        implements SDCQuadrupleContextMessageFactory<T1, T2, T3, T4> {

    public QuadrupleContextMessageFactory(Class<?> messageClass, Class<?> contextClass,
            SDCContextFactory<SDCQuadrupleContext<T1, T2, T3, T4>> contextFactory, String raw) {
        super(messageClass, contextClass, contextFactory, raw);
    }

    public SDCContextFactory<SDCQuadrupleContext<T1, T2, T3, T4>> getContextFactory() {
        return (SDCContextFactory<SDCQuadrupleContext<T1, T2, T3, T4>>) super.getContextFactory();
    }

}
