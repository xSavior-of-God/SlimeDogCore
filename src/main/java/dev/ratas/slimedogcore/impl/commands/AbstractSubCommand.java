package dev.ratas.slimedogcore.impl.commands;

import dev.ratas.slimedogcore.api.commands.SDCSubCommand;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public abstract class AbstractSubCommand implements SDCSubCommand {
    private final String name;
    private final String perms;
    private final String usage;
    private final boolean showOnTabComplete;
    private final boolean isPlayerOnly;

    protected AbstractSubCommand(String name, String perms, String usage) {
        this(name, perms, usage, true);
    }

    protected AbstractSubCommand(String name, String perms, String usage, boolean showOnTabComplete) {
        this(name, perms, usage, showOnTabComplete, false);
    }

    protected AbstractSubCommand(String name, String perms, String usage, boolean showOnTabComplete,
            boolean isPlayerOnly) {
        this.name = name;
        this.perms = perms;
        this.usage = usage;
        this.showOnTabComplete = showOnTabComplete;
        this.isPlayerOnly = isPlayerOnly;
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

    @Override
    public boolean isPlayerOnly() {
        return isPlayerOnly;
    }

}
