package dev.ratas.slimedogcore.api.messaging.context;

/**
 * Void context defines a context based on no placeholders and replacements.
 */
public interface SDCVoidContext extends SDCContext {

    default int getNumberOfPlaceholders() {
        return 0;
    }

}
