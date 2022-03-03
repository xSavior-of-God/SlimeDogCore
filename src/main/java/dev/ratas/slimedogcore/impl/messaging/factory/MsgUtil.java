package dev.ratas.slimedogcore.impl.messaging.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import dev.ratas.slimedogcore.api.messaging.context.factory.SDCSingleContextFactory;
import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.api.messaging.factory.SDCDoubleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCQuadrupleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCSingleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCTripleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCVoidContextMessageFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.SingleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.VoidContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.delegating.DelegatingDoubleContextFactory;
import dev.ratas.slimedogcore.impl.messaging.context.factory.delegating.DelegatingMultipleToOneContextFactory;
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
            String placeholder1, Function<T1, String> mapper1, String placeholder2, Function<T2, String> mapper2,
            String placeholder3, Function<T3, String> mapper3, String placeholder4, Function<T4, String> mapper4,
            String raw) {
        return new QuadrupleContextMessageFactory<>(
                new DelegatingQuadrupleContextFactory<>(new SingleContextFactory<>(placeholder1, mapper1),
                        new SingleContextFactory<>(placeholder2, mapper2),
                        new SingleContextFactory<>(placeholder3, mapper3),
                        new SingleContextFactory<>(placeholder4, mapper4)),
                raw,
                MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> twoToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> threeToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> fourToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String placeholder4, Function<T, String> mapper4, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        SingleContextFactory<T> f4 = new SingleContextFactory<>(placeholder4, mapper4);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3, f4);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> fiveToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String placeholder4, Function<T, String> mapper4, String placeholder5,
            Function<T, String> mapper5, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        SingleContextFactory<T> f4 = new SingleContextFactory<>(placeholder4, mapper4);
        SingleContextFactory<T> f5 = new SingleContextFactory<>(placeholder5, mapper5);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3, f4, f5);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> sixToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String placeholder4, Function<T, String> mapper4, String placeholder5,
            Function<T, String> mapper5, String placeholder6, Function<T, String> mapper6, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        SingleContextFactory<T> f4 = new SingleContextFactory<>(placeholder4, mapper4);
        SingleContextFactory<T> f5 = new SingleContextFactory<>(placeholder5, mapper5);
        SingleContextFactory<T> f6 = new SingleContextFactory<>(placeholder6, mapper6);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3, f4, f5,
                f6);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> sevenToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String placeholder4, Function<T, String> mapper4, String placeholder5,
            Function<T, String> mapper5, String placeholder6, Function<T, String> mapper6, String placeholder7,
            Function<T, String> mapper7, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        SingleContextFactory<T> f4 = new SingleContextFactory<>(placeholder4, mapper4);
        SingleContextFactory<T> f5 = new SingleContextFactory<>(placeholder5, mapper5);
        SingleContextFactory<T> f6 = new SingleContextFactory<>(placeholder6, mapper6);
        SingleContextFactory<T> f7 = new SingleContextFactory<>(placeholder7, mapper7);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3, f4, f5,
                f6, f7);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> eightToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String placeholder4, Function<T, String> mapper4, String placeholder5,
            Function<T, String> mapper5, String placeholder6, Function<T, String> mapper6, String placeholder7,
            Function<T, String> mapper7, String placeholder8, Function<T, String> mapper8, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        SingleContextFactory<T> f4 = new SingleContextFactory<>(placeholder4, mapper4);
        SingleContextFactory<T> f5 = new SingleContextFactory<>(placeholder5, mapper5);
        SingleContextFactory<T> f6 = new SingleContextFactory<>(placeholder6, mapper6);
        SingleContextFactory<T> f7 = new SingleContextFactory<>(placeholder7, mapper7);
        SingleContextFactory<T> f8 = new SingleContextFactory<>(placeholder8, mapper8);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3, f4, f5,
                f6, f7, f8);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> nineToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String placeholder4, Function<T, String> mapper4, String placeholder5,
            Function<T, String> mapper5, String placeholder6, Function<T, String> mapper6, String placeholder7,
            Function<T, String> mapper7, String placeholder8, Function<T, String> mapper8, String placeholder9,
            Function<T, String> mapper9, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        SingleContextFactory<T> f4 = new SingleContextFactory<>(placeholder4, mapper4);
        SingleContextFactory<T> f5 = new SingleContextFactory<>(placeholder5, mapper5);
        SingleContextFactory<T> f6 = new SingleContextFactory<>(placeholder6, mapper6);
        SingleContextFactory<T> f7 = new SingleContextFactory<>(placeholder7, mapper7);
        SingleContextFactory<T> f8 = new SingleContextFactory<>(placeholder8, mapper8);
        SingleContextFactory<T> f9 = new SingleContextFactory<>(placeholder9, mapper9);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3, f4, f5,
                f6, f7, f8, f9);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static <T> SDCSingleContextMessageFactory<T> tenToOneContextDelegator(String placeholder1,
            Function<T, String> mapper1, String placeholder2, Function<T, String> mapper2, String placeholder3,
            Function<T, String> mapper3, String placeholder4, Function<T, String> mapper4, String placeholder5,
            Function<T, String> mapper5, String placeholder6, Function<T, String> mapper6, String placeholder7,
            Function<T, String> mapper7, String placeholder8, Function<T, String> mapper8, String placeholder9,
            Function<T, String> mapper9, String placeholder10, Function<T, String> mapper10, String raw) {
        SingleContextFactory<T> f1 = new SingleContextFactory<>(placeholder1, mapper1);
        SingleContextFactory<T> f2 = new SingleContextFactory<>(placeholder2, mapper2);
        SingleContextFactory<T> f3 = new SingleContextFactory<>(placeholder3, mapper3);
        SingleContextFactory<T> f4 = new SingleContextFactory<>(placeholder4, mapper4);
        SingleContextFactory<T> f5 = new SingleContextFactory<>(placeholder5, mapper5);
        SingleContextFactory<T> f6 = new SingleContextFactory<>(placeholder6, mapper6);
        SingleContextFactory<T> f7 = new SingleContextFactory<>(placeholder7, mapper7);
        SingleContextFactory<T> f8 = new SingleContextFactory<>(placeholder8, mapper8);
        SingleContextFactory<T> f9 = new SingleContextFactory<>(placeholder9, mapper9);
        SingleContextFactory<T> f10 = new SingleContextFactory<>(placeholder10, mapper10);
        DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(f1, f2, f3, f4, f5,
                f6, f7, f8, f9, f10);
        return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
    }

    public static class MultipleToOneBuilder<T> {
        private final String raw;
        private final List<SDCSingleContextFactory<T>> delegates = new ArrayList<>();

        public MultipleToOneBuilder(String raw) {
            this.raw = raw;
        }

        public MultipleToOneBuilder<T> with(String placeholder, Function<T, String> mapper) {
            delegates.add(new SingleContextFactory<>(placeholder, mapper));
            return this;
        }

        @SuppressWarnings("unchecked")
        public SDCSingleContextMessageFactory<T> build() {
            DelegatingMultipleToOneContextFactory<T> df = new DelegatingMultipleToOneContextFactory<>(
                    delegates.toArray(new SDCSingleContextFactory[0]));
            return new SingleContextMessageFactory<>(df, raw, MessageTarget.TEXT);
        }
    }

}
