package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCMessageFactory;

public abstract class AbstractMessageFactory<T extends SDCContext> implements SDCMessageFactory<T> {
    protected final SDCContextFactory<T> contextFactory;

    public AbstractMessageFactory(SDCContextFactory<T> contextFactory) {
        this.contextFactory = contextFactory;
    }

    @Override
    public SDCContextFactory<T> getContextFactory() {
        return contextFactory;
    }

}
