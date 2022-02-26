package dev.ratas.slimedogcore.api.messaging.context;

/**
 * Triple context defines a context based on three placeholders and replacement
 * types.
 */
public interface SDCTripleContext<T1, T2, T3> extends SDCContext {

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

    /**
     * Get the object used for the third placewholder replacement.
     *
     * @return the third replacement
     */
    T3 getContentsThree();

    default int getNumberOfPlaceholders() {
        return 3;
    }

}
