package dev.ratas.slimedogcore.impl.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

import dev.ratas.slimedogcore.api.commands.SDCCommandOption;
import dev.ratas.slimedogcore.api.commands.SDCCommandOptionSet;

public class CommandOptionSet extends HashMap<String, SDCCommandOption> implements SDCCommandOptionSet {

    @Override
    public void addOption(String raw, String value) {
        SDCCommandOption opt = new CommandOption(raw, value);
        put(raw.replace("-", ""), opt);
    }

    @Override
    public boolean hasRawOption(String raw) {
        return hasOption(raw.replace("-", ""));
    }

    @Override
    public boolean hasOption(String name) {
        return containsKey(name);
    }

    @Override
    public Collection<SDCCommandOption> getOptions() {
        return values();
    }

    @Override
    public <T> T getValue(String name, Function<String, T> converter, T def) {
        SDCCommandOption opt = get(name);
        if (opt == null || !opt.hasValue()) {
            return def;
        }
        return converter.apply(opt.getValue());
    }

}
