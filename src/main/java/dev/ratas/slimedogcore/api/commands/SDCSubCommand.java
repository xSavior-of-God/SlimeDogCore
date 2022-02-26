package dev.ratas.slimedogcore.api.commands;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public interface SDCSubCommand extends SDCCommandPart {

    boolean hasPermission(SDCRecipient sender);

    String getUsage(SDCRecipient sender, String[] args);

    String getName();

}
