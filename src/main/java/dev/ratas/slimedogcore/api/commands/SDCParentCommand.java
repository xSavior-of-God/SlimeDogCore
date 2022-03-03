package dev.ratas.slimedogcore.api.commands;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public interface SDCParentCommand extends SDCCommandPart {

    SDCSubCommand getSubCommand(String name);

    void addSubCommand(SDCSubCommand subCommand);

    String getUsage(SDCRecipient recipient);

}
