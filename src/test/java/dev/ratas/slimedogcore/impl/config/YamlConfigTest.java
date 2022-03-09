package dev.ratas.slimedogcore.impl.config;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.impl.mock.MockResourceProvider;

public class YamlConfigTest {
    private File realConfigFile;
    private CustomYamlConfig config;

    @BeforeEach
    public void setup() {
        realConfigFile = getFrom("src/test/resources/config.yml".split("/"));
        config = new CustomYamlConfig(new MockResourceProvider(), realConfigFile, false);
    }

    @Test
    public void test_configNotNull() {
        Assertions.assertNotNull(config, "Config should not be null");
    }

    @Test
    public void test_configRefersToCorrectFile() {
        Assertions.assertEquals(realConfigFile, config.getFile(), "Config should refer to the correct file");
    }

    @Test
    public void test_configNotEmpty() {
        Assertions.assertFalse(config.getConfig().getValues(true).isEmpty(), "Config should not be empty");
    }

    @Test
    public void test_configHasCorrectNumberOfKeys() {
        Assertions.assertEquals(config.getConfig().getKeys(true).size(), 1, "Should have one key");
        Assertions.assertEquals(config.getConfig().getValues(true).size(), 1, "Should have one value");
    }

    @Test
    public void test_configHasCorrectKey() {
        Assertions.assertEquals(config.getConfig().getKeys(true).iterator().next(), "key", "Key should be 'key'");
        Assertions.assertEquals(config.getConfig().getValues(true).entrySet().iterator().next().getKey(), "key",
                "Key of value should be 'key'");
    }

    @Test
    public void test_configHasCorrectValue() {
        Assertions.assertEquals(config.getConfig().getValues(true).entrySet().iterator().next().getValue(), "value",
                "Value should be'value'");
    }

    public File getFrom(String... paths) {
        File folder = null;
        for (String path : paths) {
            if (folder == null) {
                folder = new File(path);
            } else {
                folder = new File(folder, path);
            }
        }
        return folder;
    }

}
