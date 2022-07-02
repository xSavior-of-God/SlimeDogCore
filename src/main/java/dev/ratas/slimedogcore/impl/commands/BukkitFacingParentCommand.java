package dev.ratas.slimedogcore.impl.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import dev.ratas.slimedogcore.api.commands.SDCCommandOption;
import dev.ratas.slimedogcore.impl.wrappers.BukkitAdapter;

public abstract class BukkitFacingParentCommand extends AbstractParentCommand implements TabExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return onTabComplete(BukkitAdapter.adapt(sender), args);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OptionParser opts = OptionParser.parseArgs(args);
        return onOptionedCommand(BukkitAdapter.adapt(sender), opts.args, opts.opts);
    }

    public static class OptionParser {
        private final String[] args;
        private final List<SDCCommandOption> opts;

        public OptionParser(String[] args, List<SDCCommandOption> opts) {
            this.args = args;
            this.opts = opts;
        }

        public String[] getArgs() {
            return args;
        }

        public List<SDCCommandOption> getOpts() {
            return opts;
        }

        public static OptionParser parseArgs(String[] args) {
            List<String> argList = new ArrayList<>();
            List<SDCCommandOption> opts = new ArrayList<>();
            String curOpt = null;
            for (String arg : args) {
                if (arg.startsWith("--")) { // option
                    if (curOpt != null) { // end previous
                        opts.add(new CommandOption(curOpt, null));
                    }
                    curOpt = arg;
                } else {
                    if (curOpt != null) { // value for previous option
                        opts.add(new CommandOption(curOpt, arg));
                        curOpt = null;
                    } else { // regular argument
                        argList.add(arg);
                    }
                }
            }
            if (curOpt != null) {
                opts.add(new CommandOption(curOpt, null));
            }
            return new OptionParser(argList.toArray(new String[argList.size()]), opts);
        }
    }

}
