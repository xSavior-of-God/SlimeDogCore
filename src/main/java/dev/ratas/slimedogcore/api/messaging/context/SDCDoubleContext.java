package dev.ratas.slimedogcore.api.messaging.context;

/**
 * Double context defines a context based on two placeholders and replacement
 * types.
 */
public interface SDCDoubleContext<T1, T2> extends SDCContext {

    /**
     * Get the object used for the first placeholder replacement.
     *
     * @return the first replacement
     */
    T1 getContentsOne();

    /**
     * Get the object used for the second placewholder replacement.
     *
     * @return the second replacement
     */
    T2 getContentsTwo();

    default int getNumberOfPlaceholders() {
        return 2;
    }

}
