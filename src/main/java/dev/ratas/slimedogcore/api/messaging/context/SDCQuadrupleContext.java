package dev.ratas.slimedogcore.api.messaging.context;

/**
 * QuadrupleContext defines a context that includes 4 placeholders and
 * replacements of 4 (potentially) different types.
 */
public interface SDCQuadrupleContext<T1, T2, T3, T4> extends SDCContext {

    /**
     * Get the object used for the first placewholder replacement.
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

    /**
     * Get the object used for the fourth placewholder replacement.
     *
     * @return the fourth replacement
     */
    T4 getContentsFour();

    default int getNumberOfPlaceholders() {
        return 4;
    }

}
