package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.Message;
import dev.ratas.slimedogcore.api.messaging.context.VoidContext;

public interface VoidContextMessageFactory extends MessageFactory<VoidContext> {

    default Message<VoidContext> getMessage() {
        return getMessage(null);
    }

}
