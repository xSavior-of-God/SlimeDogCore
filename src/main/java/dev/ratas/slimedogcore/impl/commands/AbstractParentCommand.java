package dev.ratas.slimedogcore.impl.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

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

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], getApplicableSubCommandNames(sender), new ArrayList<>());
        }
        SDCSubCommand subCommand = getSubCommand(args[0]);
        if (subCommand == null || !subCommand.hasPermission(sender)) {
            return Collections.emptyList();
        }
        return subCommand.onTabComplete(sender, removeFirstArg(args));
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args, List<String> opts) {
        if (args.length == 0) {
            return false;
        }
        SDCSubCommand subCommand = getSubCommand(args[0]);
        if (subCommand == null || !subCommand.hasPermission(sender)) {
            return false;
        }
        if (!subCommand.onCommand(sender, removeFirstArg(args), opts)) {
            sender.sendMessage(subCommand.getUsage(sender, args));
        }
        return true;
    }
    
    protected String[] removeFirstArg(String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }

    protected List<String> getApplicableSubCommandNames(CommandSender sender) {
        List<String> names = new ArrayList<>();
        for (SDCSubCommand subCommand : subCommands.values()) {
            if (subCommand.hasPermission(sender)) {
                names.add(subCommand.getName());
            }
        }
        return names;
    }

}
