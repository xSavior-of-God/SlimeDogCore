package dev.ratas.slimedogcore.api.commands;

public interface SDCParentCommand extends SDCCommandPart {

    SDCSubCommand getSubCommand(String name);

    void addSubCommand(SDCSubCommand subCommand);

}
