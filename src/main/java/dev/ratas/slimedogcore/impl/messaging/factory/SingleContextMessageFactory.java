package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCSingleContextMessageFactory;

public class SingleContextMessageFactory<T> extends MessageFactory<SDCSingleContext<T>>
        implements SDCSingleContextMessageFactory<T> {

    public SingleContextMessageFactory(Class<?> messageClass, Class<?> contextClass,
            SDCSingleContextFactory<T> contextFactory, String raw) {
        super(messageClass, contextClass, contextFactory, raw);
    }

    @Override
    public SDCSingleContextFactory<T> getContextFactory() {
        return (SDCSingleContextFactory<T>) super.getContextFactory();
    }

}
