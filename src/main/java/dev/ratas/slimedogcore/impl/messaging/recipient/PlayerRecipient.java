package dev.ratas.slimedogcore.impl.messaging.recipient;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCPlayerRecipient;

public class PlayerRecipient extends MessageRecipient implements SDCPlayerRecipient {

    public PlayerRecipient(Player delegate) {
        super(delegate);
    }

    @Override
    public Location getLoaction() {
        return ((Player) delegate).getLocation();
    }

}
