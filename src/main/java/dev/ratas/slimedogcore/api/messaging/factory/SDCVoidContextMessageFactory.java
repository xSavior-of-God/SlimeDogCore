package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCVoidContext;

public interface SDCVoidContextMessageFactory extends SDCMessageFactory<SDCVoidContext> {

    default SDCMessage<SDCVoidContext> getMessage() {
        return getMessage(null);
    }

}
