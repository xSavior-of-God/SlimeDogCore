package dev.ratas.slimedogcore.impl.commands;

import java.util.HashMap;
import java.util.Map;

import dev.ratas.slimedogcore.api.commands.SDCParentCommand;
import dev.ratas.slimedogcore.api.commands.SDCSubCommand;
import dev.ratas.slimedogcore.impl.utils.StringIgnoreCase;

public abstract class AbstractParentCommand implements SDCParentCommand {
    private final Map<StringIgnoreCase, SDCSubCommand> subCommands = new HashMap<>();

    @Override
    public SDCSubCommand getSubCommand(String name) {
        return subCommands.get(new StringIgnoreCase(name));
    }

    @Override
    public void addSubCommand(SDCSubCommand subCommand) {
        subCommands.put(new StringIgnoreCase(subCommand.getName()), subCommand);
    }

}
