package dev.ratas.slimedogcore.api.messaging.context.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCVoidContext;

public interface SDCVoidContextFactory extends SDCContextFactory<SDCVoidContext> {

    SDCVoidContext getContext();

}
