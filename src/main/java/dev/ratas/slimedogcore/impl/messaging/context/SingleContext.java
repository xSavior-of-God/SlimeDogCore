package dev.ratas.slimedogcore.impl.messaging.context;

import java.util.function.Function;

import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;

public class SingleContext<T> implements SDCSingleContext<T> {
    private final String placeholder;
    private final T t;
    private final Function<T, String> mapper;

    public SingleContext(String placeholder, T t, Function<T, String> mapper) {
        this.placeholder = placeholder;
        this.t = t;
        this.mapper = mapper;
    }

    @Override
    public String fill(String msg) {
        return msg.replace(placeholder, mapper.apply(t));
    }

    @Override
    public T getContents() {
        return t;
    }

}
