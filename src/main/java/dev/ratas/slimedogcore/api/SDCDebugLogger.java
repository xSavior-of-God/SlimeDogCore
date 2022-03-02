package dev.ratas.slimedogcore.api;

public interface SDCDebugLogger {

    /**
     * Get the prefix of the debug logger;
     *
     * @return the prefix of the logger
     */
    String getPrefix();

    /**
     * Check if the debug logger is enabled.
     *
     * @return true if the debug mode is enabled, false otherwise
     */
    boolean isEnabled();

    /**
     * Send a debug message to console. If debug mode is disabled, no message will
     * be sent.
     *
     * @param msg the message
     */
    void log(String msg);

}
