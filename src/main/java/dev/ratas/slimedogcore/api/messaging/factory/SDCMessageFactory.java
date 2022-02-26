package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;

public interface SDCMessageFactory<C extends SDCContext> {

    SDCContextFactory<C> getContextFactory();

    SDCMessage<C> getMessage(C context);
}
