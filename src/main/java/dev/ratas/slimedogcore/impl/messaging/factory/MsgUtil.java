package dev.ratas.slimedogcore.impl.messaging.factory;

import java.util.function.Function;

import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.factory.SDCDoubleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCQuadrupleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCSingleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCTripleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCVoidContextMessageFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.SingleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.VoidContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.delegating.DelegatingDoubleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.delegating.DelegatingQuadrupleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.delegating.DelegatingTripleContextFactory;

public final class MsgUtil {

    private MsgUtil() {
        // private constructor for utility class
    }

    public static SDCVoidContextMessageFactory voidContext(String raw) {
        return new VoidContextMessageFactory(VoidContextFactory.INSTANCE, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> singleContext(String placeholder, Function<T, String> mapper,
            String raw) {
        return new SingleContextMessageFactory<>(new SingleContextFactory<>(placeholder, mapper), raw,
                MessageTarget.TEXT);
    }

    public static <T1, T2> SDCDoubleContextMessageFactory<T1, T2> doubleContext(String placeholder1,
            Function<T1, String> mapper1, String placeholder2, Function<T2, String> mapper2,
            String raw) {
        return new DoubleContextMessageFactory<>(
                new DelegatingDoubleContextFactory<>(new SingleContextFactory<>(placeholder1, mapper1),
                        new SingleContextFactory<>(placeholder2, mapper2)),
                raw,
                MessageTarget.TEXT);
    }

    public static <T1, T2, T3> SDCTripleContextMessageFactory<T1, T2, T3> tripleContext(String placeholder1,
            Function<T1, String> mapper1, String placeholder2, Function<T2, String> mapper2, String placeholder3,
            Function<T3, String> mapper3, String raw) {
        return new TripleContextMessageFactory<>(
                new DelegatingTripleContextFactory<>(new SingleContextFactory<>(placeholder1, mapper1),
                        new SingleContextFactory<>(placeholder2, mapper2),
                        new SingleContextFactory<>(placeholder3, mapper3)),
                raw,
                MessageTarget.TEXT);
    }

    public static <T1, T2, T3, T4> SDCQuadrupleContextMessageFactory<T1, T2, T3, T4> quadrupleContext(
            String placeholder1,
            Function<T1, String> mapper1, String placeholder2, Function<T2, String> mapper2, String placeholder3,
            Function<T3, String> mapper3, String placeholder4, Function<T4, String> mapper4, String raw) {
        return new QuadrupleContextMessageFactory<>(
                new DelegatingQuadrupleContextFactory<>(new SingleContextFactory<>(placeholder1, mapper1),
                        new SingleContextFactory<>(placeholder2, mapper2),
                        new SingleContextFactory<>(placeholder3, mapper3),
                        new SingleContextFactory<>(placeholder4, mapper4)),
                raw,
                MessageTarget.TEXT);
    }

}
