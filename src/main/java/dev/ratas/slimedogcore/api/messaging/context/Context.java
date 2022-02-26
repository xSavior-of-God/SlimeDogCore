package dev.ratas.slimedogcore.api.messaging.context;

public interface Context {

    String fill(String msg);

    int getNumberOfPlaceholders();

}
