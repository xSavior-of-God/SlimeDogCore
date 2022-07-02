package dev.ratas.slimedogcore.impl.commands.mock;

import java.util.List;
import java.util.function.Consumer;

import dev.ratas.slimedogcore.api.commands.SDCCommandOption;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
import dev.ratas.slimedogcore.impl.commands.AbstractSubCommand;

public class MockSubCommand extends AbstractSubCommand {
    private final Consumer<String[]> onTabComplete;
    private final Consumer<String[]> onCommand;

    public MockSubCommand(String name, String perms, String usage, Consumer<String[]> onTabComplete,
            Consumer<String[]> onCommand) {
        this(new Options.Builder().withName(name).withPerms(perms).withUsage(usage).build(), onTabComplete, onCommand);
    }

    public MockSubCommand(Options settings, Consumer<String[]> onTabComplete, Consumer<String[]> onCommand) {
        super(settings);
        this.onTabComplete = onTabComplete;
        this.onCommand = onCommand;
    }

    @Override
    public List<String> onTabComplete(SDCRecipient sender, String[] args) {
        onTabComplete.accept(args);
        return null;
    }

    @Override
    public boolean onOptionedCommand(SDCRecipient sender, String[] args, List<SDCCommandOption> opts) {
        onCommand.accept(args);
        return false;
    }

}
