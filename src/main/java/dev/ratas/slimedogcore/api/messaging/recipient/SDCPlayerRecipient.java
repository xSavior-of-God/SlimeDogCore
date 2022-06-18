package dev.ratas.slimedogcore.api.messaging.recipient;

import java.util.UUID;

import org.bukkit.Location;

public interface SDCPlayerRecipient extends SDCRecipient {

    /**
     * Typo-d name for getLocation
     *
     * @return
     */
    @Deprecated
    Location getLoaction();

    /**
     * Get the location of the player.
     *
     * @return the location of the player
     */
    Location getLocation();

    /**
     * Get the name of this player.
     *
     * @return the player's name
     */
    String getName();

    /**
     * Get the UUID of the player.
     *
     * @return the player's UUID
     */
    UUID getId();

}
