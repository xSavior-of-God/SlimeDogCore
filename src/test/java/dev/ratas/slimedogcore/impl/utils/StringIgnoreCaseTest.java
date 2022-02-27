package dev.ratas.slimedogcore.impl.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringIgnoreCaseTest {

    @Test
    public void test_StringIgnoreCaseAreEqual_SameCase() {
        StringIgnoreCase sic1 = new StringIgnoreCase("somestring");
        StringIgnoreCase sic2 = new StringIgnoreCase("somestring");
        Assertions.assertEquals(sic1, sic2, "Expected same-case StringIgnoreCase instances to be equal");
    }

    @Test
    public void test_StringIgnoreCaseAreEqual_OtherCase() {
        StringIgnoreCase sic1 = new StringIgnoreCase("somestring");
        StringIgnoreCase sic2 = new StringIgnoreCase("SOMESTRING");
        Assertions.assertEquals(sic1, sic2, "Expected other-case StringIgnoreCase instances to be equal");
    }

    @Test
    public void test_StringIgnoreCaseAreEqual_CamelCase() {
        StringIgnoreCase sic1 = new StringIgnoreCase("somestring");
        StringIgnoreCase sic2 = new StringIgnoreCase("SomeString");
        Assertions.assertEquals(sic1, sic2, "Expected CamelCase StringIgnoreCase instances to be equal");
    }

    @Test
    public void test_differentNotEqual1() {
        StringIgnoreCase sic1 = new StringIgnoreCase("somestring");
        StringIgnoreCase sic2 = new StringIgnoreCase("somestr1ng");
        Assertions.assertNotEquals(sic1, sic2, "Expected differnet StringIgnoreCase instances to be different");
    }

    @Test
    public void test_differentNotEqual2() {
        StringIgnoreCase sic1 = new StringIgnoreCase("somestring");
        StringIgnoreCase sic2 = new StringIgnoreCase("somestring1");
        Assertions.assertNotEquals(sic1, sic2, "Expected differnet StringIgnoreCase instances to be different");
    }

    @Test
    public void test_sameHashCodeNotEqual() {
        String a = "Siblings";
        String b = "Teheran";
        Assertions.assertEquals(a.hashCode(), b.hashCode(), "Expected these strings to have the same hash code");
        StringIgnoreCase sic1 = new StringIgnoreCase(a);
        StringIgnoreCase sic2 = new StringIgnoreCase(b);
        Assertions.assertNotEquals(sic1, sic2,
                "Expected differnet StringIgnoreCase instances to be different (despite having the same hash code)");

    }

}
