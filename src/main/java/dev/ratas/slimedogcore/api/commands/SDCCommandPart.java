package dev.ratas.slimedogcore.api.commands;

import java.util.List;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
import dev.ratas.slimedogcore.impl.commands.CommandOption;

public interface SDCCommandPart {

    List<String> onTabComplete(SDCRecipient sender, String[] args);

    @Deprecated
    default boolean onCommand(SDCRecipient sender, String[] args, List<String> opts) {
        return onOptionedCommand(sender, args, CommandOption.convertFromString(opts));
    }

    boolean onOptionedCommand(SDCRecipient sender, String[] args, SDCCommandOptionSet opts);

}
