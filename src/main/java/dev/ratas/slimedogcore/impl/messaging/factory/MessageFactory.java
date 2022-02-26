package dev.ratas.slimedogcore.impl.messaging.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.Validate;

import dev.ratas.slimedogcore.api.messaging.SDCMessage;
import dev.ratas.slimedogcore.api.messaging.context.SDCContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCContextFactory;

public class MessageFactory<T extends SDCContext> extends AbstractMessageFactory<T> {
    private final Class<?> messageClass;
    private final Class<?> contextClass;
    private final Constructor<SDCMessage<T>> messageConstructor;
    private final String raw;

    @SuppressWarnings("unchecked")
    public MessageFactory(Class<?> messageClass, Class<?> contextClass, SDCContextFactory<T> contextFactory,
            String raw) {
        super(contextFactory);
        Validate.isTrue(SDCMessage.class.isAssignableFrom(messageClass), "Illegal message class: " + messageClass);
        Validate.isTrue(SDCContext.class.isAssignableFrom(contextClass), "Illegal context class: " + contextClass);
        this.messageClass = messageClass;
        this.contextClass = contextClass;
        this.raw = raw;
        Constructor<SDCMessage<T>> msgConstructor;
        try {
            msgConstructor = (Constructor<SDCMessage<T>>) this.messageClass.getConstructor(String.class,
                    this.contextClass);
        } catch (NoSuchMethodException | SecurityException e) {
            try { // due to type parameters being reduced to Object in runtime
                msgConstructor = (Constructor<SDCMessage<T>>) this.messageClass.getConstructor(String.class,
                        Object.class);
            } catch (NoSuchMethodException | SecurityException e1) {
                e.printStackTrace();
                throw new IllegalArgumentException(e1);
            }
        }
        this.messageConstructor = msgConstructor;
    }

    @Override
    public SDCMessage<T> getMessage(T context) {
        try {
            return messageConstructor.newInstance(raw, context);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
