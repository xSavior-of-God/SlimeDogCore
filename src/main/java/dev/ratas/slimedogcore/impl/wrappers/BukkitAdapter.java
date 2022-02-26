package dev.ratas.slimedogcore.impl.wrappers;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
import dev.ratas.slimedogcore.impl.messaging.recipient.MessageRecipient;

public final class BukkitAdapter {

    private BukkitAdapter() {
        // private constructor
    }

    public static SDCRecipient adapt(CommandSender sender) {
        return new MessageRecipient(sender);
    }

}
