package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCDoubleContextMessageFactory;

public class DoubleContextMessageFactory<T1, T2> extends MessageFactory<SDCDoubleContext<T1, T2>>
        implements SDCDoubleContextMessageFactory<T1, T2> {

    public DoubleContextMessageFactory(Class<?> messageClass, Class<?> contextClass,
            SDCContextFactory<SDCDoubleContext<T1, T2>> contextFactory, String raw) {
        super(messageClass, contextClass, contextFactory, raw);
    }

    @Override
    public SDCContextFactory<SDCDoubleContext<T1, T2>> getContextFactory() {
        return (SDCContextFactory<SDCDoubleContext<T1, T2>>) super.getContextFactory();
    }

}
