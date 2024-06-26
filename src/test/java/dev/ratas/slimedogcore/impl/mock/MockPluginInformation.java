package dev.ratas.slimedogcore.impl.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dev.ratas.slimedogcore.api.wrappers.SDCPluginInformation;

public class MockPluginInformation implements SDCPluginInformation {
    private static final String PLUGIN_VERSION = "PL.TEST_TIME";
    private static final String MC_VERSION = "MC.TEST_TIME";
    private static final String CB_PACKAGE_NAME = "V_TEST_R";
    private static final String PLUGIN_NAME = "TestSDCPlugin";
    private static final List<String> AUTHORS = Collections
            .unmodifiableList(Arrays.asList("SlimeDog", "drives_a_ford"));

    @Override
    public String getPluginVersion() {
        return PLUGIN_VERSION;
    }

    @Override
    public String getMCVersion() {
        return MC_VERSION;
    }

    @Override
    public String getCraftBukkitPackage() {
        return CB_PACKAGE_NAME;
    }

    @Override
    public String getCraftBukkitFullPackage() {
        return CB_PACKAGE_NAME;
    }

    @Override
    public String getPluginName() {
        return PLUGIN_NAME;
    }

    @Override
    public List<String> getAuthors() {
        return AUTHORS;
    }

}
