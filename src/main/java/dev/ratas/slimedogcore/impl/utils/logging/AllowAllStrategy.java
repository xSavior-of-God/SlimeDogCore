package dev.ratas.slimedogcore.impl.utils.logging;

import dev.ratas.slimedogcore.api.utils.logger.SDCSpamStrategy;

public class AllowAllStrategy implements SDCSpamStrategy {

    @Override
    public boolean shouldSend(String msg) {
        return true;
    }

}
