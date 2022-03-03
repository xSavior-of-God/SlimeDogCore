package dev.ratas.slimedogcore.impl.messaging.factory;

import java.util.function.BiConsumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.messaging.factory.SDCDoubleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCQuadrupleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCSingleContextMessageFactory;
import dev.ratas.slimedogcore.api.messaging.factory.SDCTripleContextMessageFactory;
import dev.ratas.slimedogcore.impl.commands.mock.MockRecipient;

public class MsgUtilsTest {
    private MockRecipient recipient;
    private BiConsumer<String, Boolean> onMessage;

    private void assertNextMessage(String expected, boolean shouldBeJson) {
        onMessage = (msg, isJson) -> {
            if (shouldBeJson) {
                Assertions.assertTrue(isJson, "Message is json");
            } else {
                Assertions.assertFalse(isJson, "Message isn't json");
            }
            Assertions.assertEquals(expected, msg, "Message different from expected message");
        };
    }

    private <T> void assertNextSingleMessage(SDCSingleContextMessageFactory<T> mf, T val, String expected,
            boolean shouldBeJson) {
        assertNextMessage(expected, shouldBeJson);
        recipient = new MockRecipient(onMessage);
        mf.getMessage(mf.getContextFactory().getContext(val)).sendTo(recipient);
    }

    private <T1, T2> void assertNextDoubleMessage(SDCDoubleContextMessageFactory<T1, T2> mf, T1 val1, T2 val2,
            String expected, boolean shouldBeJson) {
        assertNextMessage(expected, shouldBeJson);
        recipient = new MockRecipient(onMessage);
        mf.getMessage(mf.getContextFactory().getContext(val1, val2)).sendTo(recipient);
    }

    private <T1, T2, T3> void assertNextTripleMessage(SDCTripleContextMessageFactory<T1, T2, T3> mf, T1 val1, T2 val2,
            T3 val3, String expected, boolean shouldBeJson) {
        assertNextMessage(expected, shouldBeJson);
        recipient = new MockRecipient(onMessage);
        mf.getMessage(mf.getContextFactory().getContext(val1, val2, val3)).sendTo(recipient);
    }

    private <T1, T2, T3, T4> void assertNextQuadrupleMessage(SDCQuadrupleContextMessageFactory<T1, T2, T3, T4> mf,
            T1 val1, T2 val2, T3 val3, T4 val4, String expected, boolean shouldBeJson) {
        assertNextMessage(expected, shouldBeJson);
        recipient = new MockRecipient(onMessage);
        mf.getMessage(mf.getContextFactory().getContext(val1, val2, val3, val4)).sendTo(recipient);
    }

    @Test
    public void test_singleContextTest() {
        String ph = "(ph)";
        String raw = "raw message " + ph + " and more";
        SDCSingleContextMessageFactory<Integer> mf = MsgUtil.singleContext(ph, s -> String.valueOf(s + 1), raw);
        int val = 10;
        assertNextSingleMessage(mf, val, raw.replace(ph, String.valueOf(val + 1)), false);
    }

    @Test
    public void test_doubleContextTest() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String raw = "raw message " + ph1 + " and more with " + ph2 + "";
        SDCDoubleContextMessageFactory<Integer, Double> mf = MsgUtil.doubleContext(ph1, s -> String.valueOf(s - 1), ph2,
                d -> String.valueOf(d), raw);
        int val1 = 10;
        double val2 = -0.45;
        assertNextDoubleMessage(mf, val1, val2,
                raw.replace(ph1, String.valueOf(val1 - 1)).replace(ph2, String.valueOf(val2)), false);
    }

    @Test
    public void test_tripleContextTest() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String raw = "raw message " + ph1 + " and more with " + ph2 + " and " + ph3;
        SDCTripleContextMessageFactory<Integer, Double, String> mf = MsgUtil.tripleContext(ph1,
                s -> String.valueOf(s - 1), ph2, d -> String.valueOf(d), ph3, s -> s, raw);
        int val1 = 10;
        double val2 = -0.45;
        String val3 = "REPALCEMENT";
        assertNextTripleMessage(mf, val1, val2, val3,
                raw.replace(ph1, String.valueOf(val1 - 1)).replace(ph2, String.valueOf(val2)).replace(ph3, val3),
                false);
    }

    @Test
    public void test_quadrupleContextTest() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String raw = "raw message " + ph1 + " and more with " + ph2 + " and " + ph3 + " PH4: " + ph4;
        SDCQuadrupleContextMessageFactory<Integer, Double, String, Class<?>> mf = MsgUtil.quadrupleContext(ph1,
                s -> String.valueOf(s - 1), ph2, d -> String.valueOf(d), ph3, s -> s, ph4, c -> c.getName(),
                raw);
        int val1 = 10;
        double val2 = -0.45;
        String val3 = "REPALCEMENT";
        Class<MsgUtil> val4 = MsgUtil.class;
        assertNextQuadrupleMessage(mf, val1, val2, val3, val4,
                raw.replace(ph1, String.valueOf(val1 - 1)).replace(ph2, String.valueOf(val2)).replace(ph3, val3)
                        .replace(ph4, val4.getName()),
                false);
    }

    @Test
    public void test_twoToOneReplacesBoth() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String raw = "raw (2->1) message " + ph1 + " and more with " + ph2 + "";
        SDCSingleContextMessageFactory<String> mf = MsgUtil.twoToOneContextDelegator(ph1, s -> s, ph2, s -> s, raw);
        String replacement = "some STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement), false);
    }

    @Test
    public void test_twoToOneReplacesaSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String raw = "raw (2->1) message " + ph1 + " and more with " + ph2 + "";
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.twoToOneContextDelegator(ph1,
                s -> String.valueOf(s.t), ph2, s -> String.valueOf(s.u), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair, raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)),
                false);
    }

    @Test
    public void test_threeToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String raw = ph3 + " raw (3->1) message " + ph1 + " and more with " + ph2 + "";
        SDCSingleContextMessageFactory<String> mf = MsgUtil.twoToOneContextDelegator(ph1, p -> p, ph2, p -> p, raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement), false);
    }

    @Test
    public void test_threeToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String raw = ph3 + " raw (3->1) message " + ph1 + " and more with " + ph2 + "";
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.threeToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()),
                false);
    }

    @Test
    public void test_fourToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String raw = ph3 + " raw (3->1) message " + ph1 + " and more with " + ph2 + " and then " + ph4;
        SDCSingleContextMessageFactory<String> mf = MsgUtil.fourToOneContextDelegator(ph1, p -> p, ph2, p -> p, ph3,
                p -> p, ph4, p -> p, raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement), false);
    }

    @Test
    public void test_fourToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String raw = ph3 + " raw (3->1) message " + ph1 + " and more with " + ph2 + " still " + ph4;
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.fourToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), ph4,
                p -> p.getClass().getPackageName(), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName()),
                false);
    }

    @Test
    public void test_fiveToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String raw = ph3 + ph5 + " raw (3->1) message " + ph1 + " and more with " + ph2 + " and then " + ph4;
        SDCSingleContextMessageFactory<String> mf = MsgUtil.fiveToOneContextDelegator(ph1, p -> p, ph2, p -> p, ph3,
                p -> p, ph4, p -> p, ph5, p -> p, raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement).replace(ph5, replacement), false);
    }

    @Test
    public void test_fiveToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String raw = ph3 + " raw (3->1) message " + ph1 + ph5 + " and more with " + ph2 + " still " + ph4;
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.fiveToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), ph4,
                p -> p.getClass().getPackageName(), ph5, p -> String.valueOf(p.u + p.t), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName())
                        .replace(ph5, String.valueOf(pair.u + pair.t)),
                false);
    }

    @Test
    public void test_sixToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String raw = ph3 + ph5 + " raw (3->1) message " + ph1 + " and more with " + ph2 + " and then " + ph4 + ph6;
        SDCSingleContextMessageFactory<String> mf = MsgUtil.sixToOneContextDelegator(ph1, p -> p, ph2, p -> p, ph3,
                p -> p, ph4, p -> p, ph5, p -> p, ph6, p -> p, raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement).replace(ph5, replacement)
                .replace(ph6, replacement), false);
    }

    @Test
    public void test_sixToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String raw = ph3 + " raw (3->1) message " + ph1 + ph5 + " and more with " + ph6 + ph2 + " still " + ph4;
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.sixToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), ph4,
                p -> p.getClass().getPackageName(), ph5, p -> String.valueOf(p.u + p.t), ph6,
                p -> String.valueOf(p.u - p.t), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName())
                        .replace(ph5, String.valueOf(pair.u + pair.t)).replace(ph6, String.valueOf(pair.u - pair.t)),
                false);
    }

    @Test
    public void test_sevenToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String raw = ph3 + ph5 + " raw (3->1) message " + ph7 + ph1 + " and more with " + ph2 + " and then " + ph4
                + ph6;
        SDCSingleContextMessageFactory<String> mf = MsgUtil.sevenToOneContextDelegator(ph1, p -> p, ph2, p -> p, ph3,
                p -> p, ph4, p -> p, ph5, p -> p, ph6, p -> p, ph7, p -> p, raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement).replace(ph5, replacement)
                .replace(ph6, replacement).replace(ph7, replacement), false);
    }

    @Test
    public void test_sevenToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String raw = ph3 + ph7 + " raw (3->1) message " + ph1 + ph5 + " and more with " + ph6 + ph2 + " still " + ph4;
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.sevenToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), ph4,
                p -> p.getClass().getPackageName(), ph5, p -> String.valueOf(p.u + p.t), ph6,
                p -> String.valueOf(p.u - p.t), ph7, p -> String.valueOf(p.t - p.u), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName())
                        .replace(ph5, String.valueOf(pair.u + pair.t)).replace(ph6, String.valueOf(pair.u - pair.t))
                        .replace(ph7, String.valueOf(pair.t - pair.u)),
                false);
    }

    @Test
    public void test_eightToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String raw = ph3 + ph5 + " raw (3->1) message " + ph7 + ph1 + " and more with " + ph2 + ph8 + " and then " + ph4
                + ph6;
        SDCSingleContextMessageFactory<String> mf = MsgUtil.eightToOneContextDelegator(ph1, p -> p, ph2, p -> p, ph3,
                p -> p, ph4, p -> p, ph5, p -> p, ph6, p -> p, ph7, p -> p, ph8, p -> p, raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement).replace(ph5, replacement)
                .replace(ph6, replacement).replace(ph7, replacement).replace(ph8, replacement), false);
    }

    @Test
    public void test_eightToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String raw = ph8 + ph3 + ph7 + " raw (3->1) message " + ph1 + ph5 + " and more with " + ph6 + ph2 + " still "
                + ph4;
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.eightToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), ph4,
                p -> p.getClass().getPackageName(), ph5, p -> String.valueOf(p.u + p.t), ph6,
                p -> String.valueOf(p.u - p.t), ph7, p -> String.valueOf(p.t - p.u), ph8,
                p -> String.valueOf(p.t * p.u), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName())
                        .replace(ph5, String.valueOf(pair.u + pair.t)).replace(ph6, String.valueOf(pair.u - pair.t))
                        .replace(ph7, String.valueOf(pair.t - pair.u)).replace(ph8, String.valueOf(pair.u * pair.t)),
                false);
    }

    @Test
    public void test_nineToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String ph9 = "(ph9)";
        String raw = ph3 + ph5 + " raw (3->1) message " + ph9 + ph7 + ph1 + " and more with " + ph2 + ph8 + " and then "
                + ph4 + ph6;
        SDCSingleContextMessageFactory<String> mf = MsgUtil.nineToOneContextDelegator(ph1, p -> p, ph2, p -> p, ph3,
                p -> p, ph4, p -> p, ph5, p -> p, ph6, p -> p, ph7, p -> p, ph8, p -> p, ph9, p -> p, raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement).replace(ph5, replacement)
                .replace(ph6, replacement).replace(ph7, replacement).replace(ph8, replacement)
                .replace(ph9, replacement), false);
    }

    @Test
    public void test_nineToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String ph9 = "(ph9)";
        String raw = ph8 + ph3 + ph9 + ph7 + " raw (3->1) message " + ph1 + ph5 + " and more with " + ph6 + ph2
                + " still " + ph4;
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.nineToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), ph4,
                p -> p.getClass().getPackageName(), ph5, p -> String.valueOf(p.u + p.t), ph6,
                p -> String.valueOf(p.u - p.t), ph7, p -> String.valueOf(p.t - p.u), ph8,
                p -> String.valueOf(p.t * p.u), ph9, p -> String.valueOf(p.t * p.t), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName())
                        .replace(ph5, String.valueOf(pair.u + pair.t)).replace(ph6, String.valueOf(pair.u - pair.t))
                        .replace(ph7, String.valueOf(pair.t - pair.u)).replace(ph8, String.valueOf(pair.u * pair.t))
                        .replace(ph9, String.valueOf(pair.t * pair.t)),
                false);
    }

    @Test
    public void test_tenToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String ph9 = "(ph9)";
        String ph10 = "(ph10)";
        String raw = ph3 + ph5 + " raw (3->1) message " + ph7 + ph1 + " and more with " + ph2 + ph8 + ph10
                + " and then " + ph4 + ph9 + ph6;
        SDCSingleContextMessageFactory<String> mf = MsgUtil.tenToOneContextDelegator(ph1, p -> p, ph2, p -> p, ph3,
                p -> p, ph4, p -> p, ph5, p -> p, ph6, p -> p, ph7, p -> p, ph8, p -> p, ph9, p -> p, ph10, p -> p,
                raw);
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement).replace(ph5, replacement)
                .replace(ph6, replacement).replace(ph7, replacement).replace(ph8, replacement)
                .replace(ph9, replacement).replace(ph10, replacement), false);
    }

    @Test
    public void test_tenToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String ph9 = "(ph9)";
        String ph10 = "(ph10)";
        String raw = ph8 + ph3 + ph7 + ph10 + ph9 + " raw (3->1) message " + ph1 + ph5 + " and more with " + ph6 + ph2
                + " still " + ph4;
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = MsgUtil.tenToOneContextDelegator(ph1,
                p -> String.valueOf(p.t), ph2, p -> String.valueOf(p.u), ph3, p -> p.getClass().getName(), ph4,
                p -> p.getClass().getPackageName(), ph5, p -> String.valueOf(p.u + p.t), ph6,
                p -> String.valueOf(p.u - p.t), ph7, p -> String.valueOf(p.t - p.u), ph8,
                p -> String.valueOf(p.t * p.u), ph9, p -> String.valueOf(p.t * p.t), ph10,
                p -> String.valueOf(p.u * p.u), raw);
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName())
                        .replace(ph5, String.valueOf(pair.u + pair.t)).replace(ph6, String.valueOf(pair.u - pair.t))
                        .replace(ph7, String.valueOf(pair.t - pair.u)).replace(ph8, String.valueOf(pair.u * pair.t))
                        .replace(ph9, String.valueOf(pair.t * pair.t)).replace(ph10, String.valueOf(pair.u * pair.u)),
                false);
    }

    @Test
    public void test_tenBuilderToOneReplacesAll() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String ph9 = "(ph9)";
        String ph10 = "(ph10)";
        String raw = ph3 + ph5 + " raw (3->1) message " + ph7 + ph1 + " and more with " + ph2 + ph8 + ph10
                + " and then " + ph4 + ph9 + ph6;
        MsgUtil.MultipleToOneBuilder<String> builder = new MsgUtil.MultipleToOneBuilder<>(raw);
        builder.with(ph1, p -> p);
        builder.with(ph2, p -> p);
        builder.with(ph3, p -> p);
        builder.with(ph4, p -> p);
        builder.with(ph5, p -> p);
        builder.with(ph6, p -> p);
        builder.with(ph7, p -> p);
        builder.with(ph8, p -> p);
        builder.with(ph9, p -> p);
        builder.with(ph10, p -> p);
        SDCSingleContextMessageFactory<String> mf = builder.build();
        String replacement = "some () STR";
        assertNextSingleMessage(mf, replacement, raw.replace(ph1, replacement).replace(ph2, replacement)
                .replace(ph3, replacement).replace(ph4, replacement).replace(ph5, replacement)
                .replace(ph6, replacement).replace(ph7, replacement).replace(ph8, replacement)
                .replace(ph9, replacement).replace(ph10, replacement), false);
    }

    @Test
    public void test_tenBuilderToOneReplacesAllSeparately() {
        String ph1 = "(ph1)";
        String ph2 = "(ph2)";
        String ph3 = "(ph3)";
        String ph4 = "(ph4)";
        String ph5 = "(ph5)";
        String ph6 = "(ph6)";
        String ph7 = "(ph7)";
        String ph8 = "(ph8)";
        String ph9 = "(ph9)";
        String ph10 = "(ph10)";
        String raw = ph8 + ph3 + ph7 + ph10 + ph9 + " raw (3->1) message " + ph1 + ph5 + " and more with " + ph6 + ph2
                + " still " + ph4;
        MsgUtil.MultipleToOneBuilder<Pair<Integer, Double>> builder = new MsgUtil.MultipleToOneBuilder<>(raw);
        builder.with(ph1, p -> String.valueOf(p.t));
        builder.with(ph2, p -> String.valueOf(p.u));
        builder.with(ph3, p -> p.getClass().getName());
        builder.with(ph4, p -> p.getClass().getPackageName());
        builder.with(ph5, p -> String.valueOf(p.u + p.t));
        builder.with(ph6, p -> String.valueOf(p.u - p.t));
        builder.with(ph7, p -> String.valueOf(p.t - p.u));
        builder.with(ph8, p -> String.valueOf(p.t * p.u));
        builder.with(ph9, p -> String.valueOf(p.t * p.t));
        builder.with(ph10, p -> String.valueOf(p.u * p.u));
        SDCSingleContextMessageFactory<Pair<Integer, Double>> mf = builder.build();
        int val1 = -10;
        double val2 = 4.5;
        Pair<Integer, Double> pair = new Pair<>(val1, val2);
        assertNextSingleMessage(mf, pair,
                raw.replace(ph1, String.valueOf(pair.t)).replace(ph2, String.valueOf(pair.u)).replace(ph3,
                        pair.getClass().getName()).replace(ph4, pair.getClass().getPackageName())
                        .replace(ph5, String.valueOf(pair.u + pair.t)).replace(ph6, String.valueOf(pair.u - pair.t))
                        .replace(ph7, String.valueOf(pair.t - pair.u)).replace(ph8, String.valueOf(pair.u * pair.t))
                        .replace(ph9, String.valueOf(pair.t * pair.t)).replace(ph10, String.valueOf(pair.u * pair.u)),
                false);
    }

    public final static class Pair<T, U> {
        private final T t;
        private final U u;

        public Pair(T t, U u) {
            this.t = t;
            this.u = u;
        }

        public T getFirst() {
            return t;
        }

        public U getSecond() {
            return u;
        }
    }

}
