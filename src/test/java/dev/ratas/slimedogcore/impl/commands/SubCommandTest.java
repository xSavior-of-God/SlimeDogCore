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
    private Consumer<String> onSend = null;

    @BeforeEach
    public void setup() {
        onSend = null;
        times = new AtomicInteger(0);
        Consumer<String[]> consumer = (args) -> {
            times.incrementAndGet();
            Assertions.assertEquals(args.length, 2, "Should have two arguments");
            Assertions.assertEquals(ARGUMENTS[0], args[0], "Should have first argument as 'with'");
            Assertions.assertEquals(ARGUMENTS[1], args[1], "Should have second argument as 'args'");
        };
        sub = new MockSubCommand(SUB_NAME, SUB_PERM, SUB_USAGE, consumer, consumer);
        recipient = new MockRecipient((m, b) -> {
            if (onSend != null) {
                onSend.accept(m);
            }
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
    public void test_SubCommand_foundWithLowercase() {
        curArgs[0] = SUB_NAME.toLowerCase();
        parent.onCommand(recipient, ALL_ARGS.toArray(new String[0]), Collections.emptyList());
    }

    @Test
    public void test_noArgsSendsUsage() {
        String usage = parent.getUsage(recipient);
        AtomicInteger times = new AtomicInteger(0);
        onSend = msg -> {
            times.incrementAndGet();
            Assertions.assertEquals(usage, msg);
        };
        parent.onCommand(recipient, new String[] {}, Collections.emptyList());
        Assertions.assertEquals(1, times.get(), "The usage should sent exactly once");
    }

    @Test
    public void test_noArgsSendsUsageOfBothSubs() {
        MockSubCommand sub2 = new MockSubCommand("othersub", "some.perms", "/use some other one", null, null);
        parent.addSubCommand(sub2);
        String usage = parent.getUsage(recipient);
        AtomicInteger times = new AtomicInteger(0);
        onSend = msg -> {
            times.incrementAndGet();
            Assertions.assertEquals(usage, msg);
        };
        parent.onCommand(recipient, new String[] {}, Collections.emptyList());
        Assertions.assertEquals(2, usage.split("\n").length, "Should have the usage for both sub-commands");
        Assertions.assertTrue(usage.contains(sub.getUsage(recipient, new String[] {})),
                "Should contain usage for first sub-command");
        Assertions.assertTrue(usage.contains(sub2.getUsage(recipient, new String[] {})),
                "Should contain usage for second sub-command");
        Assertions.assertEquals(1, times.get(), "The usage should sent exactly once");
    }

    @Test
    public void test_SettingsConstructorIdentical() {
        String name = "some-name";
        String perms = "some.perms.as-well";
        String usage = "/not usage";
        boolean playerOnly = false;
        boolean show = true;
        MockSubCommand sub1 = new MockSubCommand(new AbstractSubCommand.Options.Builder().withName(name)
                .withPerms(perms).withUsage(usage).isPlayerOnly(playerOnly).showOnTabComplete(show).build(), null,
                null);
        MockSubCommand sub2 = new MockSubCommand(name, perms, usage, null, null);
        Assertions.assertEquals(sub1.getName(), sub2.getName());
        Assertions.assertEquals(sub1.isPlayerOnly(), sub2.isPlayerOnly());
        Assertions.assertEquals(playerOnly, sub2.isPlayerOnly());
        Assertions.assertEquals(sub1.showOnTabComplete(), sub2.showOnTabComplete());
    }

    @Test
    public void test_SettingsConstructor_correct() {
        String name = "some-name";
        String perms = "some.perms.as-well";
        String usage = "/not usage";
        boolean playerOnly = false;
        boolean show = true;
        MockSubCommand sub1 = new MockSubCommand(new AbstractSubCommand.Options.Builder().withName(name)
                .withPerms(perms).withUsage(usage).isPlayerOnly(playerOnly).showOnTabComplete(show).build(), null,
                null);
        Assertions.assertEquals(sub1.getName(), name);
        Assertions.assertEquals(sub1.showOnTabComplete(), sub1.showOnTabComplete());
        Assertions.assertEquals(show, sub1.showOnTabComplete());
    }

}
