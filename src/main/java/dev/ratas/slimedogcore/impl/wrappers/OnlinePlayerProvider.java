package dev.ratas.slimedogcore.impl.wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCPlayerRecipient;
import dev.ratas.slimedogcore.api.wrappers.SDCOnlinePlayerProvider;
import dev.ratas.slimedogcore.impl.SlimeDogCore;
import dev.ratas.slimedogcore.impl.messaging.recipient.PlayerRecipient;

public class OnlinePlayerProvider implements SDCOnlinePlayerProvider {
    private final SlimeDogCore plugin;

    public OnlinePlayerProvider(SlimeDogCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public SDCPlayerRecipient getPlayerByName(String name) {
        Player player = plugin.getServer().getPlayer(name);
        if (player == null) {
            return null;
        }
        return new PlayerRecipient(player);
    }

    @Override
    public SDCPlayerRecipient getPlayerByID(UUID id) {
        Player player = plugin.getServer().getPlayer(id);
        if (player == null) {
            return null;
        }
        return new PlayerRecipient(player);
    }

    @Override
    public List<SDCPlayerRecipient> getAllPlayers() {
        List<SDCPlayerRecipient> players = new ArrayList<>();
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            players.add(new PlayerRecipient(player));
        }
        return players;
    }

}
