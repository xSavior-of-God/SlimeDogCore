package dev.ratas.slimedogcore.impl.messaging.recipient;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCPlayerRecipient;

public class PlayerRecipient extends MessageRecipient implements SDCPlayerRecipient {

    public PlayerRecipient(Player delegate) {
        this(delegate, true);
    }

    public PlayerRecipient(Player delegate, boolean allowMiniMessages) {
        super(delegate, allowMiniMessages);
    }

    @Override
    public Location getLoaction() {
        return getLocation();
    }

    @Override
    public Location getLocation() {
        return ((Player) delegate).getLocation();
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public UUID getId() {
        return ((Player) delegate).getUniqueId();
    }

}
