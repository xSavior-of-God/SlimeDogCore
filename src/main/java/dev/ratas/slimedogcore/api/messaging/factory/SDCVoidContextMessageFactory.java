package dev.ratas.slimedogcore.api.messaging.factory;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCVoidContext;

/**
 * The void message factory is used to construct a message with no required
 * inputs.
 */
public interface SDCVoidContextMessageFactory extends SDCMessageFactory<SDCVoidContext> {

    /**
     * Get the message without specifying context. This is only possible for a
     * VoidContextMessage.
     *
     * @return the associated message
     */
    default SDCMessage<SDCVoidContext> getMessage() {
        return getMessage(null);
    }

}
