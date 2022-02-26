package dev.ratas.slimedogcore.api.commands;

import org.bukkit.command.CommandSender;

public interface SDCSubCommand extends SDCCommandPart {

    boolean hasPermission(CommandSender sender);

    String getUsage(CommandSender sender, String[] args);

}
