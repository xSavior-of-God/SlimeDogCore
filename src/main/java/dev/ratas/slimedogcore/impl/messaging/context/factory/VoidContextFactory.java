package dev.ratas.slimedogcore.impl.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCVoidContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCVoidContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.VoidContext;

public class VoidContextFactory implements SDCVoidContextFactory {
    public static VoidContextFactory INSTANCE = new VoidContextFactory();

    @Override
    public SDCVoidContext getContext() {
        return VoidContext.INSTANCE;
    }

}
