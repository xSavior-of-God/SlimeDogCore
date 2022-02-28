package dev.ratas.slimedogcore.impl.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.impl.commands.mock.MockParentCommand;
import dev.ratas.slimedogcore.impl.commands.mock.MockRecipient;
import dev.ratas.slimedogcore.impl.commands.mock.MockSubCommand;

public class SubCommandTest {
    private static final String SUB_NAME = "subcmd";
    private static final String SUB_PERM = "sub.perms";
    private static final String SUB_USAGE = "USAGE";
    private static final String[] ARGUMENTS = new String[] { "with", "args" };
    private static final List<String> ALL_ARGS;
    static {
        List<String> args = new ArrayList<>();
        args.add(SUB_NAME);
        for (String arg : ARGUMENTS) {
            args.add(arg);
        }
        ALL_ARGS = Collections.unmodifiableList(args);
    }
    private MockSubCommand sub;
    private MockParentCommand parent;
    private AtomicInteger times;
    private MockRecipient recipient;
    private String[] curArgs;

    @BeforeEach
    public void setup() {
        times = new AtomicInteger(0);
        Consumer<String[]> consumer = (args) -> {
            times.incrementAndGet();
            Assertions.assertEquals(args.length, 2, "Should have two arguments");
            Assertions.assertEquals(ARGUMENTS[0], args[0], "Should have first argument as 'with'");
            Assertions.assertEquals(ARGUMENTS[1], args[1], "Should have second argument as 'args'");
        };
        sub = new MockSubCommand(SUB_NAME, SUB_PERM, SUB_USAGE, consumer, consumer);
        recipient = new MockRecipient((m, b) -> {
        });
        parent = new MockParentCommand(sub);
        curArgs = Arrays.copyOf(ALL_ARGS.toArray(new String[0]), ALL_ARGS.size());
    }

    @Test // test that no call when inforrect first argument
    public void test_SubCommand_noCall_tabComplete() {
        curArgs[0] = "SOMETHING ELSE";
        parent.onTabComplete(recipient, curArgs);
        Assertions.assertEquals(0, times.get(), "The sub-command onTabComplete should not be called");
    }

    @Test // test that no call when inforrect first argument
    public void test_SubCommand_noCall_onCommand() {
        curArgs[0] = "SOMETHING ELSE";
        parent.onCommand(recipient, curArgs, Collections.emptyList());
        Assertions.assertEquals(0, times.get(), "The sub-command onTabComplete should not be called");
    }

    @Test
    public void test_SubCommand_getsCalled_TabComplete() {
        parent.onTabComplete(recipient, ALL_ARGS.toArray(new String[0]));
        Assertions.assertEquals(1, times.get(), "The sub-command onTabComplete should be called once");
    }

    @Test
    public void test_SubCommand_getsCalled_OnCommand() {
        parent.onCommand(recipient, ALL_ARGS.toArray(new String[0]), Collections.emptyList());
        Assertions.assertEquals(times.get(), 1, "The sub-command onTabComplete should be called once");
    }

    @Test
    public void test_SuCommand_foundWithUppercase() {
        curArgs[0] = SUB_NAME.toUpperCase();
        parent.onCommand(recipient, ALL_ARGS.toArray(new String[0]), Collections.emptyList());
    }

    @Test
    public void test_SuCommand_foundWithLowercase() {
        curArgs[0] = SUB_NAME.toLowerCase();
        parent.onCommand(recipient, ALL_ARGS.toArray(new String[0]), Collections.emptyList());
    }

}
