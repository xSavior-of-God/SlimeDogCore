package dev.ratas.slimedogcore.impl.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.context.SDCVoidContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.delivery.SDCMessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.factory.SDCVoidContextMessageFactory;

public class VoidContextMessageFactory extends MessageFactory<SDCVoidContext> implements SDCVoidContextMessageFactory {

    public VoidContextMessageFactory(SDCContextFactory<SDCVoidContext> contextFactory, String raw,
            MessageTarget msgTarget, SDCMessageDeliverer messageDeliverer) {
        super(contextFactory, raw, msgTarget, messageDeliverer);
    }

}
