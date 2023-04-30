package dev.ratas.slimedogcore.impl.messaging;

import dev.ratas.slimedogcore.api.reload.ReloadException;
import dev.ratas.slimedogcore.api.reload.SDCReloadable;

public class IllegalMessageException extends ReloadException {

    public IllegalMessageException(SDCReloadable reloadable, String msg) {
        super(reloadable, msg);
    }

}
