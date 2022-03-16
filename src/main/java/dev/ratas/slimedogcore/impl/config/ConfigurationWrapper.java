package dev.ratas.slimedogcore.impl.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;

import dev.ratas.slimedogcore.api.config.SDCConfiguration;

public class ConfigurationWrapper implements SDCConfiguration {
    private final ConfigurationSection delegate;

    public ConfigurationWrapper(ConfigurationSection delegate) {
        Validate.notNull(delegate, "Delegate cannot be null");
        this.delegate = delegate;
    }

    @Override
    public Collection<String> getKeys(boolean deep) {
        return delegate.getKeys(deep);
    }

    @Override
    public Map<String, Object> getValues(boolean deep) {
        return delegate.getValues(deep);
    }

    @Override
    public boolean contains(String path) {
        return delegate.contains(path);
    }

    @Override
    public boolean contains(String path, boolean ignoreDefault) {
        return delegate.contains(path, ignoreDefault);
    }

    @Override
    public boolean isSet(String path) {
        return delegate.isSet(path);
    }

    @Override
    public String getCurrentPath() {
        return delegate.getCurrentPath();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public Object get(String path) {
        return delegate.get(path);
    }

    @Override
    public Object get(String path, Object def) {
        return delegate.get(path, def);
    }

    @Override
    public String getString(String path) {
        return delegate.getString(path);
    }

    @Override
    public String getString(String path, String def) {
        return delegate.getString(path, def);
    }

    @Override
    public int getInt(String path) {
        return delegate.getInt(path);
    }

    @Override
    public int getInt(String path, int def) {
        return delegate.getInt(path, def);
    }

    @Override
    public boolean isInt(String path) {
        return delegate.isInt(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return delegate.getBoolean(path);
    }

    @Override
    public boolean getBoolean(String path, boolean def) {
        return delegate.getBoolean(path, def);
    }

    @Override
    public boolean isBoolean(String path) {
        return delegate.isBoolean(path);
    }

    @Override
    public double getDouble(String path) {
        return delegate.getDouble(path);
    }

    @Override
    public double getDouble(String path, double def) {
        return delegate.getDouble(path, def);
    }

    @Override
    public boolean isDouble(String path) {
        return delegate.isDouble(path);
    }

    @Override
    public long getLong(String path) {
        return delegate.getLong(path);
    }

    @Override
    public long getLong(String path, long def) {
        return delegate.getLong(path, def);
    }

    @Override
    public boolean isLong(String path) {
        return delegate.isLong(path);
    }

    @Override
    public List<?> getList(String path) {
        return delegate.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return delegate.getList(path, def);
    }

    @Override
    public boolean isList(String path) {
        return delegate.isList(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return delegate.getStringList(path);
    }

    @Override
    public SDCConfiguration getConfigurationSection(String path) {
        ConfigurationSection section = delegate.getConfigurationSection(path);
        if (section == null) {
            return null;
        }
        return new ConfigurationWrapper(section);
    }

    @Override
    public SDCConfiguration getDefaultSection() {
        ConfigurationSection defSect = delegate.getDefaultSection();
        if (defSect == null) {
            return null;
        }
        return new ConfigurationWrapper(defSect);
    }

}
