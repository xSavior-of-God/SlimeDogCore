package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCSingleContextMessageFactory;

public class SingleContextMessageFactory<T> extends MessageFactory<SDCSingleContext<T>>
        implements SDCSingleContextMessageFactory<T> {

    public SingleContextMessageFactory(Class<?> messageClass, Class<?> contextClass,
            SDCContextFactory<SDCSingleContext<T>> contextFactory, String raw) {
        super(messageClass, contextClass, contextFactory, raw);
    }

    @Override
    public SDCContextFactory<SDCSingleContext<T>> getContextFactory() {
        return (SDCContextFactory<SDCSingleContext<T>>) super.getContextFactory();
    }

}
