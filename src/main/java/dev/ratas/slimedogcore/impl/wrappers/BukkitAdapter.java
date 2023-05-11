package dev.ratas.slimedogcore.impl.wrappers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
import dev.ratas.slimedogcore.impl.messaging.recipient.MessageRecipient;
import dev.ratas.slimedogcore.impl.messaging.recipient.PlayerRecipient;

public final class BukkitAdapter {
    private static final boolean ALLOW_MINI_MESSAGES = true;

    private BukkitAdapter() {
        // private constructor
    }

    public static SDCRecipient adapt(CommandSender sender) {
        if (sender instanceof Player) {
            return new PlayerRecipient((Player) sender, ALLOW_MINI_MESSAGES);
        }
        return new MessageRecipient(sender, ALLOW_MINI_MESSAGES);
    }

}
