package dev.ratas.slimedogcore.impl.commands;

import dev.ratas.slimedogcore.api.commands.SDCSubCommand;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public abstract class AbstractSubCommand implements SDCSubCommand {
    private final String name;
    private final String perms;
    private final String usage;
    private final boolean showOnTabComplete;

    protected AbstractSubCommand(String name, String perms, String usage) {
        this(name, perms, usage, true);
    }

    protected AbstractSubCommand(String name, String perms, String usage, boolean showOnTabComplete) {
        this.name = name;
        this.perms = perms;
        this.usage = usage;
        this.showOnTabComplete = showOnTabComplete;
    }

    @Override
    public boolean hasPermission(SDCRecipient sender) {
        return sender.hasPermission(perms);
    }

    @Override
    public String getUsage(SDCRecipient sender, String[] args) {
        return usage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean showOnTabComplete() {
        return showOnTabComplete;
    }

}
