package dev.ratas.slimedogcore.impl.messaging.recipient;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

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
        String msg = message.getFilled();
        boolean isJson = isJson(msg);
        if (!isJson) {
            msg = color(msg);
        }
        sendTo(message.getTarget().getSpigotType(), msg, isJson);
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

    public static boolean isJson(final String json) {
        return isJson(new StringReader(json));
    }

    private static boolean isJson(final Reader reader) {
        return isJson(new JsonReader(reader));
    }

    private static boolean isJson(final JsonReader jsonReader) {
        try {
            JsonToken token;
            loop: while ((token = jsonReader.peek()) != JsonToken.END_DOCUMENT && token != null) {
                switch (token) {
                    case BEGIN_ARRAY:
                        jsonReader.beginArray();
                        break;
                    case END_ARRAY:
                        jsonReader.endArray();
                        break;
                    case BEGIN_OBJECT:
                        jsonReader.beginObject();
                        break;
                    case END_OBJECT:
                        jsonReader.endObject();
                        break;
                    case NAME:
                        jsonReader.nextName();
                        break;
                    case STRING:
                    case NUMBER:
                    case BOOLEAN:
                    case NULL:
                        jsonReader.skipValue();
                        break;
                    case END_DOCUMENT:
                        break loop;
                    default:
                        throw new AssertionError(token);
                }
            }
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

}
