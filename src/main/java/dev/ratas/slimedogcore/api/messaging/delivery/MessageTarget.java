package dev.ratas.slimedogcore.api.messaging.delivery;

import net.md_5.bungee.api.ChatMessageType;

/**
 * Defines the message target.
 */
public enum MessageTarget {
    TEXT(ChatMessageType.CHAT), ACTION_BAR(ChatMessageType.ACTION_BAR);

    private final ChatMessageType spigotType;

    MessageTarget(ChatMessageType spigotType) {
        this.spigotType = spigotType;
    }

    public ChatMessageType getSpigotType() {
        return spigotType;
    }

}
