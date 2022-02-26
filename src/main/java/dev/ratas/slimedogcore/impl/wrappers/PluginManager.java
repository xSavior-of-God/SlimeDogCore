package dev.ratas.slimedogcore.impl.wrappers;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import dev.ratas.slimedogcore.api.wrappers.SDCPluginManager;
import dev.ratas.slimedogcore.impl.SlimeDogCore;

public class PluginManager implements SDCPluginManager {
    private final SlimeDogCore plugin;

    public PluginManager(SlimeDogCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerEvents(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    @Override
    public void callEvent(Event event) {
        plugin.getServer().getPluginManager().callEvent(event);
    }

    @Override
    public JavaPlugin getPluginByName(String name) {
        Plugin plugin = this.plugin.getServer().getPluginManager().getPlugin(name);
        if (plugin instanceof JavaPlugin) {
            return (JavaPlugin) plugin;
        }
        return null;
    }

    @Override
    public <T extends JavaPlugin> T getPluginByClass(Class<T> clazz) {
        return JavaPlugin.getPlugin(clazz);
    }

}
