package dev.ratas.slimedogcore.impl.messaging;

import java.util.EnumMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.ratas.slimedogcore.api.messaging.Message;
import dev.ratas.slimedogcore.api.messaging.context.Context;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageDeliverer;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public enum DefinedMessageDeliverer implements MessageDeliverer {
    TEXT(MessageTarget.TEXT, ChatMessageType.CHAT), ACTION_BAR(MessageTarget.ACTION_BAR, ChatMessageType.ACTION_BAR);

    private static final Map<MessageTarget, DefinedMessageDeliverer> TARGET_MAP = new EnumMap<>(MessageTarget.class);
    static {
        for (DefinedMessageDeliverer dmd : values()) {
            TARGET_MAP.put(dmd.target, dmd);
        }
    }

    private final MessageTarget target;
    private final ChatMessageType chatMessageType;

    DefinedMessageDeliverer(MessageTarget target, ChatMessageType chatMessageType) {
        this.target = target;
        this.chatMessageType = chatMessageType;
    }

    @Override
    public <T extends Context> void deliver(Message<T> message, CommandSender sender) {
        sendMessage(chatMessageType, message, sender);
    }

    private <T extends Context> void sendMessage(ChatMessageType msgType, Message<T> message, CommandSender sender) {
        String msg = color(message.context().fill(message.getRaw()));
        BaseComponent[] comps = TextComponent.fromLegacyText(msg);
        if (sender instanceof Player player) {
            player.spigot().sendMessage(msgType, comps);
        } else {
            sender.spigot().sendMessage(comps);
        }
    }

    @Override
    public MessageTarget getDeliveryTarget() {
        return target;
    }

    public static DefinedMessageDeliverer getDeliverer(MessageTarget target) {
        return TARGET_MAP.get(target);
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
