package dev.ratas.slimedogcore.impl.commands.mock;

import java.util.function.Consumer;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public class MockRecipient implements SDCRecipient {
    private final Consumer<Object> onMessage;
    private final boolean hasAllPerms;

    public MockRecipient(Consumer<Object> onMessage) {
        this(onMessage, true);
    }

    public MockRecipient(Consumer<Object> onMessage, boolean hasAllPerms) {
        this.onMessage = onMessage;
        this.hasAllPerms = hasAllPerms;
    }

    @Override
    public <T extends SDCContext> void sendMessage(SDCMessage<T> message) {
        onMessage.accept(message);
    }

    @Override
    public boolean hasPermission(String perms) {
        return hasAllPerms;
    }

    @Override
    public void sendRawMessage(String msg) {
        onMessage.accept(msg);
    }

}
