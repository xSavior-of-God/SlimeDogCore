package dev.ratas.slimedogcore.impl.commands;

import org.apache.commons.lang3.Validate;

import dev.ratas.slimedogcore.api.commands.SDCSubCommand;
import dev.ratas.slimedogcore.api.messaging.recipient.SDCRecipient;

public abstract class AbstractSubCommand implements SDCSubCommand {
    private final String name;
    private final String perms;
    private final String usage;
    private final boolean showOnTabComplete;
    private final boolean isPlayerOnly;

    protected AbstractSubCommand(String name, String perms, String usage) {
        this(name, perms, usage, true);
    }

    protected AbstractSubCommand(String name, String perms, String usage, boolean showOnTabComplete) {
        this(name, perms, usage, showOnTabComplete, false);
    }

    protected AbstractSubCommand(Options settings) {
        this(settings.name, settings.perms, settings.usage, settings.showOnTabComplete, settings.isPlayerOnly);
    }

    protected AbstractSubCommand(String name, String perms, String usage, boolean showOnTabComplete,
            boolean isPlayerOnly) {
        this.name = name;
        this.perms = perms;
        this.usage = usage;
        this.showOnTabComplete = showOnTabComplete;
        this.isPlayerOnly = isPlayerOnly;
    }

    @Override
    public boolean hasPermission(SDCRecipient sender) {
        return sender.hasPermission(perms);
    }

    @Override
    public String getUsage(SDCRecipient sender, String[] args) {
        return usage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean showOnTabComplete() {
        return showOnTabComplete;
    }

    @Override
    public boolean isPlayerOnly() {
        return isPlayerOnly;
    }

    public static final class Options {
        private final String name;
        private final String usage;
        private final String perms;
        private final boolean showOnTabComplete;
        private final boolean isPlayerOnly;

        private Options(String name, String perms, String usage, boolean showOnTabComplete, boolean isPlayerOnly) {
            Validate.notNull(name, "Name cannot be null");
            Validate.notNull(perms, "Perms cannot be null");
            Validate.notNull(usage, "Usage cannot be null");
            this.name = name;
            this.perms = perms;
            this.usage = usage;
            this.showOnTabComplete = showOnTabComplete;
            this.isPlayerOnly = isPlayerOnly;
        }

        public static final class Builder {
            private String name;
            private String usage;
            private String perms;
            private boolean showOnTabComplete = true;
            private boolean isPlayerOnly = false;

            public Builder withName(String name) {
                this.name = name;
                return this;
            }

            public Builder withPerms(String perms) {
                this.perms = perms;
                return this;
            }

            public Builder withUsage(String usage) {
                this.usage = usage;
                return this;
            }

            public Builder showOnTabComplete(boolean show) {
                this.showOnTabComplete = show;
                return this;
            }

            public Builder isPlayerOnly(boolean playerOnly) {
                this.isPlayerOnly = playerOnly;
                return this;
            }

            public Options build() {
                return new Options(name, usage, perms, showOnTabComplete, isPlayerOnly);
            }

        }
    }

}
