package dev.ratas.slimedogcore.impl.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.util.StringUtil;

import dev.ratas.slimedogcore.api.commands.SDCParentCommand;
import dev.ratas.slimedogcore.api.commands.SDCSubCommand;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
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
    public List<String> onTabComplete(SDCRecipient sender, String[] args) {
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
    public boolean onCommand(SDCRecipient sender, String[] args, List<String> opts) {
        if (args.length == 0) {
            sender.sendRawMessage(getUsage(sender));
            return true;
        }
        SDCSubCommand subCommand = getSubCommand(args[0]);
        if (subCommand == null || !subCommand.hasPermission(sender)) {
            return false;
        }
        if (!subCommand.onCommand(sender, removeFirstArg(args), opts)) {
            sender.sendRawMessage(subCommand.getUsage(sender, args));
        }
        return true;
    }

    @Override
    public String getUsage(SDCRecipient recipient) {
        List<String> usages = new ArrayList<>();
        String[] args = new String[] {};
        for (String subName : getApplicableSubCommandNames(recipient)) {
            SDCSubCommand sc = getSubCommand(subName);
            usages.add(sc.getUsage(recipient, args));
        }
        return String.join("\n", usages);
    }

    protected String[] removeFirstArg(String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }

    protected List<String> getApplicableSubCommandNames(SDCRecipient sender) {
        List<String> names = new ArrayList<>();
        for (SDCSubCommand subCommand : subCommands.values()) {
            if (subCommand.hasPermission(sender)) {
                names.add(subCommand.getName());
            }
        }
        return names;
    }

}
