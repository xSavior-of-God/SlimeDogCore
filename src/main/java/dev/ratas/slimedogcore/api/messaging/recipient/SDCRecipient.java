package dev.ratas.slimedogcore.api.messaging.recipient;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;

public interface SDCRecipient {

    <T extends SDCContext> void sendMessage(SDCMessage<T> message);

    boolean hasPermission(String perms);

}
