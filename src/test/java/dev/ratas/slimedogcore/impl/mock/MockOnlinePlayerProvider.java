package dev.ratas.slimedogcore.impl.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCPlayerRecipient;
import dev.ratas.slimedogcore.api.wrappers.SDCOnlinePlayerProvider;

public class MockOnlinePlayerProvider implements SDCOnlinePlayerProvider {
    private final Map<String, SDCPlayerRecipient> playersByName = new HashMap<>();
    private final Map<UUID, SDCPlayerRecipient> playerById = new HashMap<>();

    public void addPlayer(SDCPlayerRecipient player) {
        playersByName.put(player.getName(), player);
        playerById.put(player.getId(), player);
    }

    @Override
    public SDCPlayerRecipient getPlayerByName(String name) {
        return playersByName.get(name);
    }

    @Override
    public SDCPlayerRecipient getPlayerByID(UUID id) {
        return playerById.get(id);
    }

    @Override
    public List<SDCPlayerRecipient> getAllPlayers() {
        return new ArrayList<>(playerById.values());
    }

}
