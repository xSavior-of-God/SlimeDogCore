package dev.ratas.slimedogcore.impl;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;
import dev.ratas.slimedogcore.api.config.SDCCustomConfigManager;
import dev.ratas.slimedogcore.api.config.settings.SDCBaseSettings;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
import dev.ratas.slimedogcore.api.scheduler.SDCScheduler;
import dev.ratas.slimedogcore.api.utils.logger.SDCDebugLogger;
import dev.ratas.slimedogcore.api.wrappers.SDCResourceProvider;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginInformation;
import dev.ratas.slimedogcore.api.wrappers.SDCPluginManager;
import dev.ratas.slimedogcore.api.wrappers.SDCWorldProvider;
import dev.ratas.slimedogcore.impl.config.BaseSettings;
import dev.ratas.slimedogcore.impl.config.ConfigManager;
import dev.ratas.slimedogcore.impl.messaging.recipient.MessageRecipient;
import dev.ratas.slimedogcore.impl.scheduler.Scheduler;
import dev.ratas.slimedogcore.impl.utils.logging.DebugLogger;
import dev.ratas.slimedogcore.impl.utils.logging.DisallowWithinTimeStrategy;
import dev.ratas.slimedogcore.impl.wrappers.PluginInformation;
import dev.ratas.slimedogcore.impl.wrappers.PluginManager;
import dev.ratas.slimedogcore.impl.wrappers.ResourceProvider;
import dev.ratas.slimedogcore.impl.wrappers.WorldProvider;

public abstract class SlimeDogCore extends JavaPlugin implements SlimeDogPlugin {
    private static final long BLOCK_IDENTICAL_DEBUG_MSG_MS = 10 * 1000L;
    private static final long CLEAR_DEBUG_LOGGER_CACHE_MS = 10 * 60 * 1000L; // every 10 minutes
    private final ConfigManager configManager = new ConfigManager(this);
    private final ResourceProvider resourceProvider = new ResourceProvider(this);
    private final PluginManager pluginManager = new PluginManager(this);
    private final WorldProvider worldProvider = new WorldProvider(this);
    private final Scheduler scheduler = new Scheduler(this);
    private final PluginInformation pluginInformation = new PluginInformation(this);
    private final DebugLogger debugLogger = new DebugLogger(getLogger(), () -> getBaseSettings().isDebugModeEnabled(),
            new DisallowWithinTimeStrategy(BLOCK_IDENTICAL_DEBUG_MSG_MS, CLEAR_DEBUG_LOGGER_CACHE_MS));
    private final BaseSettings baseSettings = new BaseSettings(() -> getDefaultConfig());
    private final SDCRecipient console = new MessageRecipient(getServer().getConsoleSender());

    public SlimeDogCore(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    public SlimeDogCore() {
    }

    @Override
    public void onEnable() {
        pluginEnabled();
    }

    @Override
    public void onDisable() {
        pluginDisabled();
    }

    @Override
    public File getWorldFolder() {
        return getServer().getWorldContainer();
    }

    @Override
    public SDCScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public SDCPluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public SDCWorldProvider getWorldProvider() {
        return worldProvider;
    }

    @Override
    public SDCResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    @Override
    public SDCCustomConfigManager getCustomConfigManager() {
        return configManager;
    }

    @Override
    public SDCPluginInformation getPluginInformation() {
        return pluginInformation;
    }

    @Override
    public SDCDebugLogger getDebugLogger() {
        return debugLogger;
    }

    @Override
    public SDCBaseSettings getBaseSettings() {
        return baseSettings;
    }

    @Override
    public SDCRecipient getConsoleRecipient() {
        return console;
    }

}
