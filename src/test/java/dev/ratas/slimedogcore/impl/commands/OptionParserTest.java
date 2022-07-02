package dev.ratas.slimedogcore.impl.commands;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.commands.SDCCommandOptionSet;
import dev.ratas.slimedogcore.impl.commands.BukkitFacingParentCommand.OptionParser;

public class OptionParserTest {

    private void checkEquality(String[] args, String[] expArgs, SDCCommandOptionSet expOpts) {
        OptionParser parser = OptionParser.parseArgs(args);
        Assertions.assertEquals(expArgs.length, parser.getArgs().length);
        for (int i = 0; i < expArgs.length; i++) {
            String exp = expArgs[i];
            String got = parser.getArgs()[i];
            Assertions.assertEquals(exp, got);
        }
        Assertions.assertEquals(expOpts, parser.getOpts());
    }

    @Test
    public void test_OptionParser_parses_no_options() {
        String[] args = "send me something".split(" ");
        String[] expArgs = new String[] { "send", "me", "something" };
        SDCCommandOptionSet expOpts = new CommandOptionSet();
        checkEquality(args, expArgs, expOpts);
    }

    @Test
    public void test_OptionParser_parses_simple_option() {
        String[] args = "send me --something".split(" ");
        String[] expArgs = new String[] { "send", "me" };
        SDCCommandOptionSet expOpts = CommandOption.convertFromString(Arrays.asList("--something"));
        checkEquality(args, expArgs, expOpts);
    }

    @Test
    public void test_OptionParser_parses_two_simple_options() {
        String[] args = "send me --something --more".split(" ");
        String[] expArgs = new String[] { "send", "me" };
        SDCCommandOptionSet expOpts = CommandOption.convertFromString(Arrays.asList("--something", "--more"));
        checkEquality(args, expArgs, expOpts);
    }

    @Test
    public void test_OptionParser_parses_option_with_value() {
        String[] args = "send me --something val".split(" ");
        String[] expArgs = new String[] { "send", "me" };
        CommandOptionSet expOpts = new CommandOptionSet();
        expOpts.addOption("--something", "val");
        checkEquality(args, expArgs, expOpts);
    }

    @Test
    public void test_OptionParser_parses_option_with_value_followed_by_option() {
        String[] args = "send me --something val --opt".split(" ");
        String[] expArgs = new String[] { "send", "me" };
        CommandOptionSet expOpts = new CommandOptionSet();
        expOpts.addOption("--something", "val");
        expOpts.addOption("--opt", null);
        checkEquality(args, expArgs, expOpts);
    }

}
