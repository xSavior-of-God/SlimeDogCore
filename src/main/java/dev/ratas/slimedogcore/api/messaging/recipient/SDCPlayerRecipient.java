package dev.ratas.slimedogcore.api.messaging.recipient;

import org.bukkit.Location;

public interface SDCPlayerRecipient extends SDCRecipient {

    /**
     * Typo-d name for getLocation
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

}
