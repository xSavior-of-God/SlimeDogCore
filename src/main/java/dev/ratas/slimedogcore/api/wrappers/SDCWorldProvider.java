package dev.ratas.slimedogcore.api.wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.World;

/**
 * Allows getting worlds by name or UUID.
 */
public interface SDCWorldProvider {

    /**
     * Get world by its name (if one exists).
     *
     * @param name the name of the target world
     * @return the world (if found) or null
     */
    World getWorldByName(String name);

    /**
     * Get world by its UUID (if one exists).
     *
     * @param id the UUID of the target world
     * @return the world (if found) or null
     */
    World getWorldById(UUID id);

    /**
     * Get all active worlds on the server.
     *
     * @return the list of all worlds
     */
    List<World> getAllWorlds();

    /**
     * Gets all world names within the server.
     *
     * @return the names of all the worlds
     */
    default List<String> getAllWorldNames() {
        List<String> names = new ArrayList<>();
        for (World world : getAllWorlds()) {
            names.add(world.getName());
        }
        return names;
    }

}
