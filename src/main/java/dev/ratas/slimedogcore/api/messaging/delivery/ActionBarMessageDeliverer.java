package dev.ratas.slimedogcore.api.messaging.delivery;

public interface ActionBarMessageDeliverer extends MessageDeliverer {

    default MessageTarget getDeliveryTarget() {
        return MessageTarget.ACTION_BAR;
    }

}
