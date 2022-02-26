package dev.ratas.slimedogcore.api.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface SDCCommandPart {

    List<String> onTabComplete(CommandSender sender, String[] args);

    boolean onCommand(CommandSender sender, String[] args, List<String> opts);

}
