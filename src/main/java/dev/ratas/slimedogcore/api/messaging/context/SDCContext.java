package dev.ratas.slimedogcore.api.messaging.context;

/**
 * Context is used to define the placeholder(s) and their replacement(s).
 */
public interface SDCContext {

    /**
     * Fill the placeholders within the given message.
     *
     * @param msg the message
     * @return the placeholder-filled message
     */
    String fill(String msg);

    /**
     * Get number of placeholders defined within the context.
     *
     * @return the number placeholders
     */
    int getNumberOfPlaceholders();

}
