package dev.ratas.slimedogcore.api.messaging;

import dev.ratas.slimedogcore.api.messaging.context.SDCVoidContext;

/**
 * The void context message shows that there is not context needed for this
 * message to be sent.
 */
public interface SDCVoidContextMessage extends SDCMessage<SDCVoidContext> {

}
