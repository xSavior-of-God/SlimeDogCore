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

}
