package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.Message;
import dev.ratas.slimedogcore.api.messaging.context.Context;
import dev.ratas.slimedogcore.api.messaging.context.factory.ContextFactory;

public interface MessageFactory<C extends Context> {

    ContextFactory<C> getContextFactory();

    Message<C> getMessage(C context);
}
