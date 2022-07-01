package dev.ratas.slimedogcore.api.commands;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public interface SDCSubCommand extends SDCCommandPart {

    /**
     * Check whether a receipient has permission to use this sub-command.
     *
     * @param sender the recipient
     * @return whether the recipient has permission to use this sub-command
     */
    boolean hasPermission(SDCRecipient sender);

    /**
     * Get the usage string for this sub.command.
     * The usage may depend ond the sender and/or arguments.
     *
     * @param sender the command sender
     * @param args   the arguments
     * @return the usage for this sub-command in this situation
     */
    String getUsage(SDCRecipient sender, String[] args);

    /**
     * Get the name of the sub-command
     *
     * @return the name of the sub-command
     */
    String getName();

    /**
     * Check whether or not the sub-command can be tab-completed.
     *
     * @return whether the sub-command can be tab-completed
     */
    boolean showOnTabComplete();

}
