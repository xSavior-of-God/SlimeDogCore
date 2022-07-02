package dev.ratas.slimedogcore.api.commands;

/**
 * Defines a command line option, potentially with a specified value.
 *
 * Example:
 * In case of comand:
 * /cmd player --opt1 --opt2 val2 --opt3
 * --opt1 and --opt3 are command options with no value specified and --opt2 is
 * an option with val2 specified as a value.
 */
public interface SDCCommandOption {

    /**
     * Gets the name of this option (without the dashes).
     *
     * @return the option name without dashes
     */
    String getName();

    /**
     * Gets the raw option as specified in the console (with dashes included).
     *
     * @return the raw name with dashes
     */
    String getRaw();

    /**
     * Check whether or not a value was specified for this option.
     *
     * @return
     */
    boolean hasValue();

    /**
     * Gets the value associated with this argument if specified or throws an
     * exception otherwise.
     *
     * @return the value associated with this argument
     * @throws IllegalStateException if no value specified
     */
    String getValue() throws IllegalStateException;

}
