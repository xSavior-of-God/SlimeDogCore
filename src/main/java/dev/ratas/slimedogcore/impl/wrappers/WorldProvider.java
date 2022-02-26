package dev.ratas.slimedogcore.impl.wrappers;

import java.util.List;
import java.util.UUID;

import org.bukkit.World;

import dev.ratas.slimedogcore.api.wrappers.SDCWorldProvider;
import dev.ratas.slimedogcore.impl.SlimeDogCore;

public class WorldProvider implements SDCWorldProvider {
    private final SlimeDogCore plugin;

    public WorldProvider(SlimeDogCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public World getWorldByName(String name) {
        return plugin.getServer().getWorld(name);
    }

    @Override
    public World getWorldById(UUID id) {
        return plugin.getServer().getWorld(id);
    }

    @Override
    public List<World> getAllWorlds() {
        return plugin.getServer().getWorlds();
    }

}
