package dev.ratas.slimedogcore.impl.config;

import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.config.SDCConfiguration;

public class ConfigWrapperTest {
    private static final String DEFAULT_YAML = String.join("\n",
            "key-level1:", // comment to stop one lining
            " key-level2: value", // comment to stop one lining
            " more-keys:", // comment to stop one lining
            " - List item 1", // comment to stop one lining
            " - List item 2", // comment to stop one lining
            "int-key: 1", // comment to stop one lining
            "double-key-1: 5.0", // comment to stop one lining
            "double-key-2: 5e-1", // comment to stop one lining
            "double-key-3: 6.3", // comment to stop one lining
            "double-key-4: 9.8" // comment to stop one lining
    );
    private ConfigurationWrapper wrapper;

    @BeforeEach
    public void setup() throws InvalidConfigurationException {
        wrapper = getDefaultWrapper();
    }

    private ConfigurationWrapper getDefaultWrapper() throws InvalidConfigurationException {
        YamlConfiguration yaml = new YamlConfiguration();
        yaml.loadFromString(DEFAULT_YAML);
        return new ConfigurationWrapper(yaml);
    }

    private ConfigurationWrapper getDefaultWrapperBlindly() {
        try {
            return getDefaultWrapper();
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_wrapperCreationWorks() {
        ConfigurationWrapper wrapper = getDefaultWrapperBlindly();
        Assertions.assertNotNull(wrapper, "Wrapper should not be null");
    }

    @Test
    public void test_twoWrappersHaveSaveContext() {
        ConfigurationWrapper otherWrapper = getDefaultWrapperBlindly();
        Assertions.assertEquals(wrapper.getKeys(false), otherWrapper.getKeys(false),
                "Immediate keys should  be the same");
        Assertions.assertEquals(wrapper.getKeys(true), otherWrapper.getKeys(true), "Deep keys should  be the same");
        Assertions.assertEquals(wrapper.getValues(false).size(), otherWrapper.getValues(false).size(),
                "Must have the same number of immediate values");
        Assertions.assertEquals(wrapper.getValues(true).size(), otherWrapper.getValues(true).size(),
                "Must have the same number of deep values");
    }

    @Test
    public void test_deepValuesSame() {
        ConfigurationWrapper otherWrapper = getDefaultWrapperBlindly();
        assertHassSameValues(wrapper, otherWrapper);
        assertHassSameValues(otherWrapper, wrapper);
    }

    public void assertHassSameValues(SDCConfiguration section1, SDCConfiguration section2) {
        for (String key : section1.getKeys(false)) {
            SDCConfiguration got1 = section1.getConfigurationSection(key);
            SDCConfiguration got2 = section2.getConfigurationSection(key);
            if (got1 != null && got2 != null) {
                assertHassSameValues((SDCConfiguration) got1, (SDCConfiguration) got2);
            } else {
                Assertions.assertEquals(section1.get(key), section2.get(key), "Key-value pairs should be equal");
            }
        }
    }

    @Test
    public void test_intKeyWorks() {
        Assertions.assertEquals(1, wrapper.getInt("int-key"), "getInt method should obtain 1 in this case");
        Assertions.assertEquals("1", wrapper.getString("int-key"), "getInt method should obtain 1 in this case");
    }

    @Test
    public void test_getIntParsesCorrectly() {
        Assertions.assertEquals(1, Integer.valueOf(wrapper.getString("int-key")),
                "getInt method should obtain 1 in this case");
        Assertions.assertEquals(wrapper.getInt("int-key"), Integer.valueOf(wrapper.getString("int-key")),
                "getInt method should obtain 1 in this case");
    }

    @Test
    public void test_getIntNoDefaultWhenPresent() {
        int def = 10;
        int got = wrapper.getInt("int-key", def);
        Assertions.assertEquals(1, got, "Should not return value when key present");
        Assertions.assertNotEquals(def, got, "Should not use default when key present");
    }

    @Test
    public void test_getIntDefaultWhenNoKey() {
        int def = 10;
        int got = wrapper.getInt("int-key-INVALID", def);
        Assertions.assertEquals(def, got, "Should use default when key not present present");
    }

    @Test
    public void test_intUsedAsDouble() {
        double expected = (double) wrapper.getInt("int-key");
        double got = wrapper.getDouble("int-key");
        Assertions.assertEquals(expected, got, "Integer values should be accepted as double values");
    }

    @Test
    public void test_getDoubleWorks() {
        double expected = 5.0D;
        double actual = wrapper.getDouble("double-key-1");
        Assertions.assertEquals(expected, actual, "Double value incorrect (regular)");
    }

    @Test
    public void test_getDoubleWorksForScientific() {
        double expected = 5e-1;
        double actual = wrapper.getDouble("double-key-2");
        Assertions.assertEquals(expected, actual, "Double value incorrect (scientific)");
    }

    @Test
    public void test_doubleNoDecimalWorksAsInt() {
        double expected = wrapper.getDouble("double-key-1");
        int actual = wrapper.getInt("double-key-1");
        Assertions.assertEquals(expected, actual, "Expect floating point value with 0 decimal part work as an integer");
    }

    @Test
    public void test_doubleNotWorkAsIntScientific() {
        double unexpected = wrapper.getDouble("double-key-2");
        int actual = wrapper.getInt("double-key-2");
        Assertions.assertNotEquals(unexpected, actual, "Did not expect floating point value work as an integer");
    }

    @Test
    public void test_doubleNotWorkAsIntWithDecimal() {
        double unexpected = wrapper.getDouble("double-key-3");
        int actual = wrapper.getInt("double-key-3");
        Assertions.assertNotEquals(unexpected, actual, "Did not expect floating point value work as an integer");
    }

    @Test
    public void test_doubleAsIntegerFloored1_cast() {
        int expected = (int) wrapper.getDouble("double-key-3");
        int actual = wrapper.getInt("double-key-3");
        Assertions.assertEquals(expected, actual, "Expected floating point value to round to integer (when cast)");
    }

    @Test
    public void test_doubleAsIntegerFloored1_floor() {
        double expected = Math.floor(wrapper.getDouble("double-key-3"));
        int actual = wrapper.getInt("double-key-3");
        Assertions.assertEquals(expected, actual, "Expected floating point value to round to integer");
    }

    @Test
    public void test_doubleAsIntegerFloored2_cast() {
        int expected = (int) wrapper.getDouble("double-key-4");
        int actual = wrapper.getInt("double-key-4");
        Assertions.assertEquals(expected, actual, "Expected floating point value to FLOOR to integer (when cast)");
    }

    @Test
    public void test_doubleAsIntegerFloored2_floor() {
        double expected = Math.floor(wrapper.getDouble("double-key-4"));
        int actual = wrapper.getInt("double-key-4");
        Assertions.assertEquals(expected, actual, "Expected floating point value to FLOOR to integer");
    }

    @Test
    public void test_multiSectionWorks() {
        String expected = "value";
        String actual = wrapper.getString("key-level1.key-level2");
        Assertions.assertEquals(expected, actual, "Expected strings to be the same");
    }

    @Test
    public void test_multiSectionIdenticalToSectionBassed() {
        String expected = wrapper.getString("key-level1.key-level2");
        String actual = wrapper.getConfigurationSection("key-level1").getString("key-level2");
        Assertions.assertEquals(expected, actual, "Expected strings to be the same (section based)");
    }

    @Test
    public void test_getStringListWorks() {
        List<String> list = wrapper.getStringList("key-level1.more-keys");
        Assertions.assertNotNull(list, "String list should not be null");
        Assertions.assertFalse(list.isEmpty(), "String list should not be empty");
        Assertions.assertEquals(2, list.size(), "String list should have 2 elements");
        Assertions.assertEquals("List item 1", list.get(0), "String elements should be the same");
        Assertions.assertEquals("List item 2", list.get(1), "String elements should be the same");
    }

    @Test
    public void test_getStringListNotNull() {
        List<String> list = wrapper.getStringList("INVALID.KEY-key-level1.more-keys");
        Assertions.assertNotNull(list, "String list should not be null (even with invalid key)");
        Assertions.assertTrue(list.isEmpty(), "String list should be empty (with invalid key)");
    }

    @Test
    public void test_getListWorks() {
        List<?> list = wrapper.getList("key-level1.more-keys");
        Assertions.assertNotNull(list, "String list should not be null (non-str list)");
        Assertions.assertFalse(list.isEmpty(), "String list should not be empty (non-str list)");
        Assertions.assertEquals(2, list.size(), "String list should have 2 elements (non-str list)");
        Assertions.assertEquals("List item 1", list.get(0), "String elements should be the same (non-str list)");
        Assertions.assertEquals("List item 2", list.get(1), "String elements should be the same (non-str list)");
    }

    @Test
    public void test_getListNull() {
        List<?> list = wrapper.getList("INVALID.KEY-key-level1.more-keys");
        Assertions.assertNull(list, "List should be null (with invalid key)");
    }

}
