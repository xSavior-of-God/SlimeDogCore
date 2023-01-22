package dev.ratas.slimedogcore.impl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.common.base.Charsets;

import org.apache.commons.lang3.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.ratas.slimedogcore.api.config.SDCCustomConfig;
import dev.ratas.slimedogcore.api.config.SDCConfiguration;
import dev.ratas.slimedogcore.api.config.exceptions.ConfigCreationException;
import dev.ratas.slimedogcore.api.config.exceptions.ConfigReloadException;
import dev.ratas.slimedogcore.api.config.exceptions.ConfigSaveException;
import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;

public class CustomYamlConfig implements SDCCustomConfig {
    private final SDCResourceProvider resourceProvider;
    private final File file;
    private final boolean createWhenMissing;
    private YamlConfiguration customConfig;
    private ConfigurationWrapper wrapper;

    public CustomYamlConfig(SDCResourceProvider resourceProvider, File file) {
        this(resourceProvider, file, true);
    }

    public CustomYamlConfig(SDCResourceProvider resourceProvider, File file, boolean createWhenMissing) {
        this.resourceProvider = resourceProvider;
        this.file = file;
        this.createWhenMissing = createWhenMissing;
    }

    @Override
    public void reloadConfig() throws ConfigReloadException {
        if (createWhenMissing) {
            saveDefaultConfig();
        }
        customConfig = loadConfiguration(file);
        wrapper = new ConfigurationWrapper(customConfig);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        InputStream resource = resourceProvider.getResource(file.getName());
        if (resource != null) {
            defConfigStream = new InputStreamReader(resource, Charsets.UTF_8);
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            customConfig.setDefaults(defConfig);
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public SDCConfiguration getConfig() {
        if (wrapper == null) {
            reloadConfig();
        }
        return wrapper;
    }

    @Override
    public void saveConfig() throws ConfigSaveException {
        if (customConfig == null) {
            return;
        }
        try {
            customConfig.save(file);
        } catch (IOException ex) {
            throw new ConfigSaveException(ex);
        }
    }

    @Override
    public void saveDefaultConfig() throws ConfigSaveException {
        if (!file.exists() && resourceProvider.getResource(file.getName()) != null) {
            resourceProvider.saveResource(file.getName(), false);
        }
    }

    public static YamlConfiguration loadConfiguration(File file) throws ConfigCreationException {
        Validate.notNull(file, "File cannot be null");

        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(file);
        } catch (FileNotFoundException ex) {
            // empty
        } catch (IOException | InvalidConfigurationException ex) {
            throw new ConfigCreationException(ex);
        }
        return config;
    }

}
