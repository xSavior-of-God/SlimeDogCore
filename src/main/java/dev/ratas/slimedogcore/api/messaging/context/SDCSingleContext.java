package dev.ratas.slimedogcore.api.messaging.context;

/**
 * SingleContext defines a context that includes one placeholder and
 * replacement.
 */
public interface SDCSingleContext<T> extends SDCContext {

    /**
     * Get the object used for the placeholder replacement.
     *
     * @return the replacement
     */
    T getContents();

    default int getNumberOfPlaceholders() {
        return 1;
    }

}
