package dev.ratas.slimedogcore.impl.messaging.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.messaging.context.SDCDoubleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCQuadrupleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCSingleContext;
import dev.ratas.slimedogcore.api.messaging.context.SDCTripleContext;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingDoubleContext;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingQuadrupleContext;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingQuadrupleDoubleDoubleContext;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingTripleContext;
import dev.ratas.slimedogcore.impl.messaging.context.delegating.DelegatingTripleLopsidedContext;

public class ContextTest {

    @Test
    public void test_voidCntextParsesNothing() {
        VoidContext c = VoidContext.INSTANCE;
        String raw = "raw string - no placeholders (or even if it did, nothing is replaced)";
        String output = c.fill(raw);
        Assertions.assertEquals(raw, output, "Expected void context not to change the string");
    }

    @Test
    public void test_voidCntextParsesNothingEvenWhenPresent() {
        VoidContext c = VoidContext.INSTANCE;
        String raw = "raw string - no placeholders (weh ave %PLACHOLDE% or {placeholder})";
        String output = c.fill(raw);
        Assertions.assertEquals(raw, output, "Expected void context not to change the string");
    }

    @Test
    public void test_singleContextFillsPlaceholderCurlyBrackets() {
        String ph = "{PLACEHOLDEr}";
        String replacement = "whatever";
        String raw = "Some String with " + ph + " does something";
        SingleContext<String> c = new SingleContext<>(ph, replacement, r -> r);
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph), "Output should have no placeholder");
        Assertions.assertTrue(out.contains(replacement), "Output should contain replacement");
    }

    @Test
    public void test_singleContextMultiFillNoChange() {
        String ph = "{PLACEHOLDEr}";
        String replacement = "whatever";
        String raw = "Some String with " + ph + " does something";
        SingleContext<String> c = new SingleContext<>(ph, replacement, r -> r);
        String out1 = c.fill(raw);
        String out2 = c.fill(out1);
        String out3 = c.fill(out2);
        Assertions.assertEquals(out1, out2, "Expected second fill to do nothing");
        Assertions.assertEquals(out1, out3, "Expected third fill to do nothing");
    }

    @Test
    public void test_singleContextFillsPlaceholderCurlyBracketsInt() {
        String ph = "{PLACEHOLDEr}";
        int replacement = -100;
        String raw = "Some String with " + ph + " does something";
        SingleContext<Integer> c = new SingleContext<>(ph, replacement, r -> String.valueOf(r));
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph), "Output should have no placeholder");
        Assertions.assertTrue(out.contains(String.valueOf(replacement)), "Output should contain replacement");
    }

    @Test
    public void test_singleContextFillsPlaceholderPercent() {
        String ph = "%PLACEHOLDER%";
        String replacement = "whatever";
        String raw = "Some String with " + ph + " does something";
        SingleContext<String> c = new SingleContext<String>(ph, replacement, r -> r);
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph), "Output should have no placeholder");
        Assertions.assertTrue(out.contains(replacement), "Output should contain replacement");
    }

    @Test
    public void test_singleContextFillsLeavesFakePlaceholderCurlyBrackets() {
        String ph = "{PLACEHOLDER}";
        String fph = "{FAKEPH}";
        String replacement = "whatever";
        String raw = "Some String with " + ph + " does something with " + fph + " and has fake placeholder";
        SingleContext<String> c = new SingleContext<String>(ph, replacement, r -> r);
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph), "Output should have no placeholder");
        Assertions.assertTrue(out.contains(replacement), "Output should contain replacement");
        Assertions.assertTrue(out.contains(fph), "Output should contain fake placeholder");
    }

    @Test
    public void test_singleContextFillsLeavesFakePlaceholderPercent() {
        String ph = "%PLACEHOLDER%";
        String fph = "%FAKEPH%";
        String replacement = "whatever";
        String raw = "Some String with " + ph + " does something with " + fph + " and has fake placeholder";
        SingleContext<String> c = new SingleContext<String>(ph, replacement, r -> r);
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph), "Output should have no placeholder");
        Assertions.assertTrue(out.contains(replacement), "Output should contain replacement");
        Assertions.assertTrue(out.contains(fph), "Output should contain fake placeholder");
    }

    @Test
    public void test_doubleContextFillsBothCurlyBrackets() {
        String ph1 = "{PH1}";
        int replace1 = 100;
        String ph2 = "{PH2}";
        double replace2 = 4.5D;
        String raw = "String that repalces " + ph1 + " and " + ph2 + " and has text";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCDoubleContext<Integer, Double> c = new DelegatingDoubleContext<>(delegate1, delegate2);
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph1), "Output should have no placeholder1");
        Assertions.assertFalse(out.contains(ph2), "Output should have no placeholder2");
        Assertions.assertTrue(out.contains(String.valueOf(replace1)), "Output should contain replacement");
        Assertions.assertTrue(out.contains(String.valueOf(replace2)), "Output should contain replacement");
    }

    @Test
    public void test_doubleContextIdenticalToDelegaes() {
        String ph1 = "{PH0}";
        int replace1 = 100;
        String ph2 = "{PH-1}";
        double replace2 = 4.5D;
        String raw = "String <> repalces " + ph1 + "! and " + ph2 + " and has text - FUN";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCDoubleContext<Integer, Double> c = new DelegatingDoubleContext<>(delegate1, delegate2);
        String delegated = c.fill(raw);
        String oneAtATime = delegate2.fill(delegate1.fill(raw));
        Assertions.assertEquals(delegated, oneAtATime,
                "Expected to get the same value regardless of whether or not the delegation is used");
    }

    @Test
    public void test_doubleContextOrderDoesNotMatter() {
        String ph1 = "{PH0}";
        int replace1 = 100;
        String ph2 = "{PH-1}";
        double replace2 = 4.5D;
        String raw = "String <> repalces " + ph1 + "! and " + ph2 + " and has text - FUN";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        String oneAtATime = delegate2.fill(delegate1.fill(raw));
        String inReverse = delegate1.fill(delegate2.fill(raw));
        Assertions.assertEquals(inReverse, oneAtATime, "Expected to get the same value regardless of order");
    }

    @Test
    public void test_tripleContextFillsAllCurlyBrackets() {
        String ph1 = "{PH<1>}";
        int replace1 = 100;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String raw = "One strin to save them " + ph1 + " all " + ph2 + " and one " + ph3 + " something to rule them";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCTripleContext<Integer, Double, Boolean> c = new DelegatingTripleContext<>(delegate1, delegate2, delegate3);
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph1), "Output should have no placeholder1");
        Assertions.assertFalse(out.contains(ph2), "Output should have no placeholder2");
        Assertions.assertFalse(out.contains(ph3), "Output should have no placeholder3");
        Assertions.assertTrue(out.contains(String.valueOf(replace1)), "Output should contain replacement");
        Assertions.assertTrue(out.contains(String.valueOf(replace2)), "Output should contain replacement");
        Assertions.assertTrue(out.contains(String.valueOf(replace3)), "Output should contain replacement");
    }

    @Test
    public void test_tripleContextEqualToOneAtATime() {
        String ph1 = "{PH<1>}";
        int replace1 = 100;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String raw = "One strin to save them " + ph1 + " all " + ph2 + " and one " + ph3 + " something to rule them";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCTripleContext<Integer, Double, Boolean> c = new DelegatingTripleContext<>(delegate1, delegate2, delegate3);
        String delegated = c.fill(raw);
        String oneAtATime = delegate3.fill(delegate2.fill(delegate1.fill(raw)));
        Assertions.assertEquals(delegated, oneAtATime,
                "Expected to get the same value regardless of whether or not the delegation is used");
    }

    @Test
    public void test_tripleContextOrderDoesNotMatter() {
        String ph1 = "{PH<1>}";
        int replace1 = 100;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String raw = "One strin to save them " + ph1 + " all " + ph2 + " and one " + ph3 + " something to rule them";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCTripleContext<Integer, Double, Boolean> c1 = new DelegatingTripleContext<>(delegate1, delegate2, delegate3);
        SDCTripleContext<Double, Integer, Boolean> c2 = new DelegatingTripleContext<>(delegate2, delegate1, delegate3);
        SDCTripleContext<Double, Boolean, Integer> c3 = new DelegatingTripleContext<>(delegate2, delegate3, delegate1);
        String out1 = c1.fill(raw);
        String out2 = c2.fill(raw);
        String out3 = c3.fill(raw);
        Assertions.assertEquals(out1, out2, "Expected to get the same value regardless of order(1)");
        Assertions.assertEquals(out1, out3, "Expected to get the same value regardless of order(2)");
    }

    @Test
    public void test_tripleContextLopsideWorks() {
        String ph1 = "{PH<1>}";
        int replace1 = 100;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String raw = "One strin to save them " + ph1 + " all " + ph2 + " and one " + ph3 + " something to rule them";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCDoubleContext<Integer, Double> dd1 = new DelegatingDoubleContext<>(delegate1, delegate2);
        SDCTripleContext<Integer, Double, Boolean> c1 = new DelegatingTripleLopsidedContext<>(dd1, delegate3);
        SDCTripleContext<Double, Integer, Boolean> c2 = new DelegatingTripleContext<>(delegate2, delegate1, delegate3);
        String out1 = c1.fill(raw);
        String out2 = c2.fill(raw);
        Assertions.assertEquals(out1, out2, "Expected to get the same value regardless of order(1)");
    }

    @Test
    public void test_quadrupleContextFillsAllCurlyBrackets() {
        String ph1 = "{PH<1>}";
        int replace1 = 42;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String ph4 = "{PHFOUR}";
        String replace4 = "GOD knows";
        String raw = "There can " + ph1 + " only ever " + ph2 + " be the one " + ph3 + " string " + ph4
                + " and life, universe";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCSingleContext<String> delegate4 = new SingleContext<>(ph4, replace4, r -> r);
        SDCQuadrupleContext<Integer, Double, Boolean, String> c = new DelegatingQuadrupleContext<>(delegate1, delegate2,
                delegate3, delegate4);
        String out = c.fill(raw);
        Assertions.assertFalse(out.contains(ph1), "Output should have no placeholder1");
        Assertions.assertFalse(out.contains(ph2), "Output should have no placeholder2");
        Assertions.assertFalse(out.contains(ph3), "Output should have no placeholder3");
        Assertions.assertFalse(out.contains(ph4), "Output should have no placeholder4");
        Assertions.assertTrue(out.contains(String.valueOf(replace1)), "Output should contain replacement");
        Assertions.assertTrue(out.contains(String.valueOf(replace2)), "Output should contain replacement");
        Assertions.assertTrue(out.contains(String.valueOf(replace3)), "Output should contain replacement");
        Assertions.assertTrue(out.contains(String.valueOf(replace4)), "Output should contain replacement");
    }

    @Test
    public void test_QuadrupleContextEqualToOneAtATime() {
        String ph1 = "{PH<1>}";
        int replace1 = 42;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String ph4 = "{PHFOUR}";
        String replace4 = "GOD knows";
        String raw = "There can " + ph1 + " only ever " + ph2 + " be the one " + ph3 + " string " + ph4
                + " and life, universe";
        String expected = "There can " + replace1 + " only ever " + replace2 + " be the one " + replace3 + " string "
                + replace4 + " and life, universe";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCSingleContext<String> delegate4 = new SingleContext<>(ph4, replace4, r -> r);
        SDCQuadrupleContext<Integer, Double, Boolean, String> c = new DelegatingQuadrupleContext<>(delegate1, delegate2,
                delegate3, delegate4);
        String delegated = c.fill(raw);
        String oneAtATime = delegate4.fill(delegate3.fill(delegate2.fill(delegate1.fill(raw))));
        Assertions.assertEquals(expected, delegated, "Manual replacement should be the same");
        Assertions.assertEquals(delegated, oneAtATime,
                "Expected to get the same value regardless of whether or not the delegation is used");
    }

    @Test
    public void test_quadrupleContextOrderDoesNotMatter() {
        String ph1 = "{PH<1>}";
        int replace1 = 42;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String ph4 = "{PHFOUR}";
        String replace4 = "GOD knows";
        String raw = "There can " + ph1 + " only ever " + ph2 + " be the one " + ph3 + " string " + ph4
                + " and life, universe";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCSingleContext<String> delegate4 = new SingleContext<>(ph4, replace4, r -> r);
        SDCQuadrupleContext<Integer, Double, Boolean, String> c1 = new DelegatingQuadrupleContext<>(delegate1,
                delegate2, delegate3, delegate4);
        SDCQuadrupleContext<Boolean, String, Integer, Double> c2 = new DelegatingQuadrupleContext<>(delegate3,
                delegate4, delegate1, delegate2);
        SDCQuadrupleContext<Double, Integer, Boolean, String> c3 = new DelegatingQuadrupleContext<>(delegate2,
                delegate1, delegate3, delegate4);
        SDCQuadrupleContext<Integer, Double, String, Boolean> c4 = new DelegatingQuadrupleContext<>(delegate1,
                delegate2, delegate4, delegate3);
        String out1 = c1.fill(raw);
        String out2 = c2.fill(raw);
        String out3 = c3.fill(raw);
        String out4 = c4.fill(raw);
        Assertions.assertEquals(out1, out2, "Expected to get the same value regardless of order(1)");
        Assertions.assertEquals(out1, out3, "Expected to get the same value regardless of order(2)");
        Assertions.assertEquals(out1, out4, "Expected to get the same value regardless of order(3)");
    }

    @Test
    public void test_quadrupleContextDoubleDoubleIdentical() {
        String ph1 = "{PH<1>}";
        int replace1 = 42;
        String ph2 = "{PH<2>}";
        double replace2 = 4.5D;
        String ph3 = "{PH!3!}";
        boolean replace3 = false;
        String ph4 = "{PHFOUR}";
        String replace4 = "GOD knows";
        String raw = "There can " + ph1 + " only ever " + ph2 + " be the one " + ph3 + " string " + ph4
                + " and life, universe";
        SDCSingleContext<Integer> delegate1 = new SingleContext<>(ph1, replace1, r -> String.valueOf(r));
        SDCSingleContext<Double> delegate2 = new SingleContext<>(ph2, replace2, r -> String.valueOf(r));
        SDCSingleContext<Boolean> delegate3 = new SingleContext<>(ph3, replace3, r -> String.valueOf(r));
        SDCSingleContext<String> delegate4 = new SingleContext<>(ph4, replace4, r -> r);
        SDCQuadrupleContext<Integer, Double, Boolean, String> c1 = new DelegatingQuadrupleContext<>(delegate1,
                delegate2, delegate3, delegate4);
        SDCDoubleContext<Boolean, String> dd1 = new DelegatingDoubleContext<>(delegate3, delegate4);
        SDCDoubleContext<Integer, Double> dd2 = new DelegatingDoubleContext<>(delegate1, delegate2);
        SDCQuadrupleContext<Boolean, String, Integer, Double> c2 = new DelegatingQuadrupleDoubleDoubleContext<>(dd1,
                dd2);
        String out1 = c1.fill(raw);
        String out2 = c2.fill(raw);
        Assertions.assertEquals(out1, out2, "Expected to get the same value regardless of order(double-double)");
    }

}
