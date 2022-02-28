package dev.ratas.slimedogcore.impl.messaging.recipient;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class MessageRecipient implements SDCRecipient {
    protected final CommandSender delegate;

    public MessageRecipient(CommandSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T extends SDCContext> void sendMessage(SDCMessage<T> message) {
        String msg = color(message.context().fill(message.getRaw()));
        sendTo(message.getTarget().getSpigotType(), msg, false);
    }

    protected void sendTo(ChatMessageType target, String msg, boolean parseJson) {
        BaseComponent[] comps;
        if (!parseJson) {
            comps = TextComponent.fromLegacyText(msg);
        } else {
            comps = ComponentSerializer.parse(msg);
        }
        if (delegate instanceof Player) {
            ((Player) delegate).spigot().sendMessage(target, comps);
        } else {
            delegate.spigot().sendMessage(comps);
        }
    }

    @Override
    public boolean hasPermission(String perms) {
        return delegate.hasPermission(perms);
    }

    protected String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public void sendRawMessage(String msg) {
        sendTo(ChatMessageType.CHAT, msg, false);
    }

}
