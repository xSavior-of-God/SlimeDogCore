package dev.ratas.slimedogcore.impl.commands;

import org.bukkit.command.CommandSender;

import dev.ratas.slimedogcore.api.commands.SDCSubCommand;

public abstract class AbstractParentWithSubsCommand extends AbstractParentCommand implements SDCSubCommand {
    private final String name;
    private final String perms;
    private final String usage;

    protected AbstractParentWithSubsCommand(String name, String perms, String usage) {
        this.name = name;
        this.perms = perms;
        this.usage = usage;
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(perms);
    }

    @Override
    public String getUsage(CommandSender sender, String[] args) {
        return usage;
    }

    @Override
    public String getName() {
        return name;
    }

}
