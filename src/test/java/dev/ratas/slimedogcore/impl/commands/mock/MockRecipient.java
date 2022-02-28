package dev.ratas.slimedogcore.impl.commands.mock;

import java.util.function.BiConsumer;

import dev.ratas.slimedogcore.impl.messaging.recipient.MessageRecipient;
import net.md_5.bungee.api.ChatMessageType;

public class MockRecipient extends MessageRecipient {
    private final BiConsumer<Object, Boolean> onMessage;
    private final boolean hasAllPerms;

    public MockRecipient(BiConsumer<Object, Boolean> onMessage) {
        this(onMessage, true);
    }

    public MockRecipient(BiConsumer<Object, Boolean> onMessage, boolean hasAllPerms) {
        super(null);
        this.onMessage = onMessage;
        this.hasAllPerms = hasAllPerms;
    }

    @Override
    protected void sendTo(ChatMessageType target, String msg, boolean parseJson) {
        onMessage.accept(msg, parseJson);
    }

    @Override
    public boolean hasPermission(String perms) {
        return hasAllPerms;
    }

}
