package dev.ratas.slimedogcore.impl.commands.mock;

import java.util.List;
import java.util.function.Consumer;

import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;
import dev.ratas.slimedogcore.impl.commands.AbstractSubCommand;

public class MockSubCommand extends AbstractSubCommand {
    private final Consumer<String[]> onTabComplete;
    private final Consumer<String[]> onCommand;

    public MockSubCommand(String name, String perms, String usage, Consumer<String[]> onTabComplete,
            Consumer<String[]> onCommand) {
        super(name, perms, usage);
        this.onTabComplete = onTabComplete;
        this.onCommand = onCommand;
    }

    @Override
    public List<String> onTabComplete(SDCRecipient sender, String[] args) {
        onTabComplete.accept(args);
        return null;
    }

    @Override
    public boolean onCommand(SDCRecipient sender, String[] args, List<String> opts) {
        onCommand.accept(args);
        return false;
    }

}
