package dev.ratas.slimedogcore.impl.mock;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.ratas.slimedogcore.api.wrappers.SDCPluginManager;

public class MockPluginManager implements SDCPluginManager {

    @Override
    public void registerEvents(Listener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public void callEvent(Event event) {
        // TODO Auto-generated method stub

    }

    @Override
    public JavaPlugin getPluginByName(String name) {
        return null;
    }

    @Override
    public <T extends JavaPlugin> T getPluginByClass(Class<T> clazz) {
        return null;
    }

}
