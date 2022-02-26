package dev.ratas.slimedogcore.impl.commands.mock;

import dev.ratas.slimedogcore.api.commands.SDCSubCommand;
import dev.ratas.slimedogcore.impl.commands.AbstractParentCommand;

public class MockParentCommand extends AbstractParentCommand {

    public MockParentCommand(SDCSubCommand... subCommands) {
        for (SDCSubCommand sub : subCommands) {
            addSubCommand(sub);
        }
    }

}
