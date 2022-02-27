package dev.ratas.slimedogcore.impl.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.World;

import dev.ratas.slimedogcore.api.wrappers.SDCWorldProvider;

public class MockWorldProvider implements SDCWorldProvider {
    public final Map<String, World> worlds = new HashMap<>();

    @Override
    public World getWorldByName(String name) {
        return worlds.get(name);
    }

    @Override
    public World getWorldById(UUID id) {
        return worlds.get(id.toString());
    }

    @Override
    public List<World> getAllWorlds() {
        return new ArrayList<>(worlds.values());
    }

}
