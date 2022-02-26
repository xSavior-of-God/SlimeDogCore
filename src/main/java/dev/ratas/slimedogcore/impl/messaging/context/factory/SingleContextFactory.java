package dev.ratas.slimedogcore.impl.messaging.context.factory;

import java.util.function.Function;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.SingleContext;

public class SingleContextFactory<T> implements SDCSingleContextFactory<T> {
    private final String placeholder;
    private final Function<T, String> mapper;

    public SingleContextFactory(String placeholder, Function<T, String> mapper) {
        this.placeholder = placeholder;
        this.mapper = mapper;
    }

    @Override
    public SDCSingleContext<T> getContext(T t) {
        return new SingleContext<>(placeholder, t, mapper);
    }

}
