package dev.ratas.slimedogcore.impl.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public abstract class BukkitFacingParentCommand extends AbstractParentCommand implements TabExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return onTabComplete(sender, args);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OptionParser opts = OptionParser.parseArgs(args);
        return onCommand(sender, opts.args, opts.opts);
    }

    private static class OptionParser {
        private final String[] args;
        private final List<String> opts;

        public OptionParser(String[] args, List<String> opts) {
            this.args = args;
            this.opts = opts;
        }

        public static OptionParser parseArgs(String[] args) {
            List<String> argList = new ArrayList<>();
            List<String> opts = new ArrayList<>();
            for (String arg : args) {
                if (arg.startsWith("--")) {
                    opts.add(arg);
                } else {
                    argList.add(arg);
                }
            }
            return new OptionParser(argList.toArray(new String[argList.size()]), opts);
        }
    }

}
