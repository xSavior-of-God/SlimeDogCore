package dev.ratas.slimedogcore.impl.messaging.mini;

import org.apache.commons.lang3.StringUtils;

public final class MiniMessageUtil {

    private MiniMessageUtil() {
        // prviate constructor
    }

    public static boolean textCouldBeMiniMessage(String raw) {
        // if it doesn't contain angle brackets, clearly not a mini-message
        if (!raw.contains("<")) {
            return false;
        }
        // if it has a different number of start and end brackets, not a
        // (correctly formated) mini-message
        if (StringUtils.countMatches(raw, "<") != StringUtils.countMatches(raw, ">")) {
            return false;
        }
        // if the first < is after the first >, it's _probably_ not a minimessage
        if (raw.indexOf("<") > raw.indexOf(">")) {
            return false;
        }
        return true;
    }

}
