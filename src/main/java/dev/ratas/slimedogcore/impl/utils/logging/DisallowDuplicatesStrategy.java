package dev.ratas.slimedogcore.impl.utils.logging;

import dev.ratas.slimedogcore.api.utils.logger.SDCSpamStrategy;

public class DisallowDuplicatesStrategy implements SDCSpamStrategy {
    private String lastMessage = null;

    @Override
    public boolean shouldSend(String msg) {
        boolean should;
        if (lastMessage == null) {
            should = true;
        } else {
            should = !lastMessage.equals(msg);
        }
        lastMessage = msg;
        return should;
    }

}
