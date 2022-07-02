package dev.ratas.slimedogcore.impl.commands;

import java.util.List;
import java.util.Objects;

import dev.ratas.slimedogcore.api.commands.SDCCommandOption;
import dev.ratas.slimedogcore.api.commands.SDCCommandOptionSet;

public class CommandOption implements SDCCommandOption {
    private final String name;
    private final String raw;
    private final String value;

    public CommandOption(String raw, String value) {
        this.raw = raw;
        this.name = raw.replace("-", "");
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRaw() {
        return raw;
    }

    @Override
    public boolean hasValue() {
        return value != null;
    }

    @Override
    public String getValue() throws IllegalStateException {
        if (!hasValue()) {
            throw new IllegalStateException("This option provides no value");
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommandOption)) {
            return false;
        }
        CommandOption o = (CommandOption) other;
        return o.name.equals(name) && (hasValue() ? value.equals(o.value) : !o.hasValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return String.format("{CmdOpt[%s=%s]}", raw, hasValue() ? value : "N/A");
    }

    public static SDCCommandOptionSet convertFromString(List<String> rawOptions) {
        SDCCommandOptionSet options = new CommandOptionSet();
        for (String raw : rawOptions) {
            options.addOption(raw, null);
        }
        return options;
    }

}
