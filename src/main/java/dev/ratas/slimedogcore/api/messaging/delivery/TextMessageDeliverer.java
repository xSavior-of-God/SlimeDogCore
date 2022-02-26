package dev.ratas.slimedogcore.api.messaging.delivery;

public interface TextMessageDeliverer extends MessageDeliverer {

    default MessageTarget getDeliveryTarget() {
        return MessageTarget.TEXT;
    }

}
