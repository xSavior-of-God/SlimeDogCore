package dev.ratas.slimedogcore.api.wrappers;

import java.util.List;
import java.util.UUID;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCPlayerRecipient;

public interface SDCOnlinePlayerProvider {

    /**
     * Get a player by name.
     *
     * @param name the player's name
     * @return the player or null if they are not online
     */
    SDCPlayerRecipient getPlayerByName(String name);

    /**
     * Get a player by UUID.
     *
     * @param id the player's UUID
     * @return the player or null if they are not online
     */
    SDCPlayerRecipient getPlayerByID(UUID id);

    /**
     * Get all online players
     *
     * @return all online players
     */
    List<SDCPlayerRecipient> getAllPlayers();

}
