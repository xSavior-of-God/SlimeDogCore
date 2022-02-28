package dev.ratas.slimedogcore.impl.messaging;

import java.util.function.BiConsumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.api.messaging.delivery.MessageTarget;
import dev.ratas.slimedogcore.impl.commands.mock.MockRecipient;
import dev.ratas.slimedogcore.impl.messaging.context.SingleContext;
import dev.ratas.slimedogcore.impl.messaging.context.VoidContext;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class MessageTest {
    private static final String JSON_STR = "{\"extra\":[{\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/suggested\"},\"text\":\"SOME STR\"},{\"hoverEvent\":{\"action\":\"show_text\",\"contents\":\"HOVER\"},\"text\":\" AND MORE\"}],\"text\":\"\"}";
    private MockRecipient recipient;
    private BiConsumer<String, Boolean> onMessage;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void test_rawMessageSent() {
        String raw = "RAW MESSAGE (to be expected)";
        onMessage = (msg, isJson) -> {
            Assertions.assertEquals(raw, msg, "Expected message to be equal");
        };
        recipient = new MockRecipient(onMessage, true);
        recipient.sendRawMessage(raw);
    }

    @Test
    public void test_messageSent() {
        String raw = "RAW MESSAGE (to be expected)";
        ContextMessage<?> message = new ContextMessage<>(raw, VoidContext.INSTANCE, MessageTarget.TEXT);
        onMessage = (msg, isJson) -> {
            Assertions.assertEquals(raw, msg, "Expected message to be equal");
            Assertions.assertFalse(isJson, "Expected raw string not to be a json");
        };
        recipient = new MockRecipient(onMessage, true);
        recipient.sendMessage(message);
    }

    @Test
    public void test_messageSentAndReplaced() {
        String raw = "RAW MESSAGE (to WITH {placeholder} expected)";
        int nr = -100;
        SingleContext<Integer> factory = new SingleContext<>("{placeholder}", nr, i -> String.valueOf(i));
        ContextMessage<SingleContext<Integer>> message = new ContextMessage<>(raw, factory, MessageTarget.TEXT);
        onMessage = (msg, isJson) -> {
            Assertions.assertEquals(raw.replace("{placeholder}", String.valueOf("-100")), msg,
                    "Expected message to be equal (placeholder)");
            Assertions.assertFalse(isJson, "Expected raw string not to be a json");
        };
        recipient = new MockRecipient(onMessage, true);
        recipient.sendMessage(message);
    }

    @Test
    public void test_jsonSent() {
        ContextMessage<?> message = new ContextMessage<>(JSON_STR, VoidContext.INSTANCE, MessageTarget.TEXT);
        onMessage = (msg, isJson) -> {
            Assertions.assertEquals(JSON_STR, msg, "Expected message to be equal");
            Assertions.assertTrue(isJson, "Expected correct json string to be a json");
            BaseComponent[] bc = ComponentSerializer.parse(msg);
            Assertions.assertNotNull(bc, "Expected to parse non-null BaseComponents");
        };
        recipient = new MockRecipient(onMessage, true);
        recipient.sendMessage(message);

    }

}
