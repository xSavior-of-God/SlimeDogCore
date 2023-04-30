package dev.ratas.slimedogcore.impl.messaging;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.api.messaging.factory.SDCDoubleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCQuadrupleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCSingleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCTripleContextMessageFactory;
import dev.ratas.slimedogcore.api.reload.ReloadException;
import dev.ratas.slimedogcore.api.reload.SDCReloadable;
import dev.ratas.slimedogcore.impl.messaging.factory.MessageFactory;
import dev.ratas.slimedogcore.impl.messaging.mini.MiniMessageUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl;

public abstract class MessagesBase implements SDCReloadable {
    private final SDCCustomConfig config;
    private final boolean checkMessagesUponReload;

    protected MessagesBase(SDCCustomConfig config) {
        this(config, true);
    }

    protected MessagesBase(SDCCustomConfig config, boolean checkMessagesUponReload) {
        this.config = config;
        this.checkMessagesUponReload = checkMessagesUponReload;
        config.saveDefaultConfig();
    }

    protected SDCCustomConfig getConfig() {
        return config;
    }

    protected String getRawMessage(String path, String def) {
        return config.getConfig().getString(path, def);
    }

    public void reloadConfig() {
        config.reloadConfig();
    }

    protected MessageSection getSubsection(String path) {
        return new MessageSection(config.getConfig().getConfigurationSection(path));
    }

    public boolean isEmpty() {
        return config.getConfig().getKeys(true).isEmpty();
    }

    @Override
    public void reload() throws ReloadException {
        config.reload();
        if (checkMessagesUponReload) {
            checkMessages();
        }
    }

    private List<MessageFactory<?>> getAllMessageFactories() {
        List<MessageFactory<?>> factories = new ArrayList<>();
        for (Field field : getClass().getDeclaredFields()) {
            if (field.getType().isAssignableFrom(MessageFactory.class)) {
                try {
                    factories.add((MessageFactory<?>) field.get(this));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new RuntimeException(e); // should not happen
                }
            }
        }
        return factories;
    }

    private void checkMessages() throws IllegalMessageException {
        for (MessageFactory<?> fact : getAllMessageFactories()) {
            String raw = fact.getRawMessage();
            if (MiniMessageUtil.textCouldBeMiniMessage(raw)) {
                MiniMessage mm = MiniMessage.miniMessage();
                try {
                    mm.deserialize(raw);
                } catch (ParsingExceptionImpl e) {
                    throw new IllegalMessageException(this, "Illegally formatted message: " + raw);
                }
            }
        }
    }

    public <T> SDCMessage<SDCSingleContext<T>> fill(SDCSingleContextMessageFactory<T> factory, T t) {
        return factory.getMessage(factory.getContextFactory().getContext(t));
    }

    public <T1, T2> SDCMessage<SDCDoubleContext<T1, T2>> fill(SDCDoubleContextMessageFactory<T1, T2> factory, T1 t1,
            T2 t2) {
        return factory.getMessage(factory.getContextFactory().getContext(t1, t2));
    }

    public <T1, T2, T3> SDCMessage<SDCTripleContext<T1, T2, T3>> fill(
            SDCTripleContextMessageFactory<T1, T2, T3> factory, T1 t1, T2 t2, T3 t3) {
        return factory.getMessage(factory.getContextFactory().getContext(t1, t2, t3));
    }

    public <T1, T2, T3, T4> SDCMessage<SDCQuadrupleContext<T1, T2, T3, T4>> fill(
            SDCQuadrupleContextMessageFactory<T1, T2, T3, T4> factory, T1 t1, T2 t2, T3 t3, T4 t4) {
        return factory.getMessage(factory.getContextFactory().getContext(t1, t2, t3, t4));
    }

}
