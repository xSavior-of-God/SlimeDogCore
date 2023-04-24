package dev.ratas.slimedogcore.impl.messaging.mini;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MiniMessageUtil {
    private static final Pattern LEFT_ANGLE_BRACKET = Pattern.compile("(?<=\\)b<");
    private static final Pattern RIGHT_ANGLE_BRACKET = Pattern.compile("(?<=\\)b>");

    private MiniMessageUtil() {
        // prviate constructor
    }

    public static boolean textCouldBeMiniMessage(String raw) {
        // if it doesn't contain angle brackets, clearly not a mini-message
        Matcher leftMatcher = LEFT_ANGLE_BRACKET.matcher(raw);
        if (!leftMatcher.find()) {
            return false;
        }
        // if it has a different number of start and end brackets, not a
        // (correctly formated) mini-message
        Matcher rightMatcher = RIGHT_ANGLE_BRACKET.matcher(raw);
        // 1 already counted above with #find
        long totalLeftMatches = leftMatcher.results().count() + 1;
        long totalRightMatches = rightMatcher.results().count();
        if (totalLeftMatches != totalRightMatches) {
            return false;
        }
        // if the first < is after the first >, it's _probably_ not a minimessage
        MatchResult firstLeft = leftMatcher.reset().results().findFirst().get();
        MatchResult firstRight = rightMatcher.reset().results().findFirst().get();
        if (firstLeft.start() > firstRight.start()) {
            return false;
        }
        return true;
    }

}
