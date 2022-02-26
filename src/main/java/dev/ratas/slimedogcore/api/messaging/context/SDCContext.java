package dev.ratas.slimedogcore.api.messaging.context;

public interface SDCContext {

    String fill(String msg);

    int getNumberOfPlaceholders();

}
