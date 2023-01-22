package dev.ratas.slimedogcore.api.commands;

import java.util.Collection;
import java.util.function.Function;

/**
 * Describes a set of command options (i.e --opt).
 */
public interface SDCCommandOptionSet {

    /**
     * Add an option to the set.
     *
     * @param raw   the raw option name
     * @param value the value (or null)
     */
    void addOption(String raw, String value);

    /**
     * Check if a specific raw option is within the option set.
     *
     * @param raw the raw option with dashes
     * @return
     */
    boolean hasRawOption(String raw);

    /**
     * Check if a specific raw option is within the option set.
     *
     * @param name the option without dashes
     * @return whether the option is within the option set
     */
    boolean hasOption(String name);

    /**
     * Get the options defined within this set
     *
     * @return the defined options
     */
    Collection<SDCCommandOption> getOptions();

    /**
     * Remove the option with the specified name.
     *
     * @param name the name of the option
     * @throws IllegalArgumentException if the name isn't present
     */
    void removeOptionWithName(String name) throws IllegalArgumentException;

    /**
     * Remove the option with the specified raw name.
     *
     * @param name the raw name of the option
     * @throws IllegalArgumentException if the name isn't present
     */
    void removeOptionWithRawName(String name) throws IllegalArgumentException;

    /**
     * Get the value attached to a specific option in an arbitrary type or a
     * specified default.
     *
     * @param <T>       the type of value required
     * @param name      the raw option queries
     * @param converter the converter from string to the object
     * @param def       the default value
     * @return the converted value or default
     */
    <T> T getValue(String name, Function<String, T> converter, T def);

    /**
     * Get the string value of a specific option or default.
     *
     * @param name the name of the option
     * @param def  the default value
     * @return the specified value or default
     */
    default String getStringValue(String name, String def) {
        return getValue(name, (s) -> s, def);
    }

}
