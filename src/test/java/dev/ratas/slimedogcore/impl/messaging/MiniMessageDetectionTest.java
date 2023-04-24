package dev.ratas.slimedogcore.impl.messaging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.ratas.slimedogcore.impl.messaging.mini.MiniMessageUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiniMessageDetectionTest {
    private static final String VERY_RAW_MESSAGE = "This message does not contain any formating";
    private static final String CORRECT_MINIMESSAGE_1 = "<yellow>Hello <blue>World<yellow>!";
    // with "placeholder"
    private static final String CORRECT_MINIMESSAGE_2 = "<yellow>Hello </yellow><blue>%player%</blue><yellow>!</yellow>";
    // i.e with escapes
    private static final String CORRECT_MINIMESSAGE_3 = "<yellow>Bigger than (<blue>\\><yellow>)";
    // no matches
    private static final String INCORRECT_1 = "<something<< what ever>";
    // matches but starts with greater than
    private static final String INCORRECT_2 = "> some <";

    @Test
    public void testRawMessageIsNot() {
        Assertions.assertFalse(MiniMessageUtil.textCouldBeMiniMessage(VERY_RAW_MESSAGE));
    }

    private void checkIsAndDeserialize(String raw) {
        Assertions.assertTrue(MiniMessageUtil.textCouldBeMiniMessage(raw));
        MiniMessage.miniMessage().deserialize(raw); // would throw exception if incorrect format
    }

    @Test
    public void testCorrectMiniMessageWorks() {
        checkIsAndDeserialize(CORRECT_MINIMESSAGE_1);
        ;
    }

    @Test
    public void testCorrectWithPlaceholderWorks() {
        checkIsAndDeserialize(CORRECT_MINIMESSAGE_2);
    }

    @Test
    public void testCorrectWithEscapeWorks() {
        checkIsAndDeserialize(CORRECT_MINIMESSAGE_3);
    }

    @Test
    public void doesNotWorkWithIncorrectMatching() {
        Assertions.assertFalse(MiniMessageUtil.textCouldBeMiniMessage(INCORRECT_1));
    
    }

    @Test
    public void doesNotWorkWithIncorrectOrder() {
        Assertions.assertFalse(MiniMessageUtil.textCouldBeMiniMessage(INCORRECT_2));
    }

}
