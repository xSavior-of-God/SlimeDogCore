package dev.ratas.slimedogcore.api.commands;

import java.util.List;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public interface SDCCommandPart {

    List<String> onTabComplete(SDCRecipient sender, String[] args);

    boolean onCommand(SDCRecipient sender, String[] args, List<String> opts);

}
