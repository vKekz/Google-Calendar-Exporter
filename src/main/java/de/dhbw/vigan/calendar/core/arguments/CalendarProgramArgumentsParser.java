package de.dhbw.vigan.calendar.core.arguments;

import java.util.HashMap;

/**
 * Represents a helper class that is used to load program arguments.
 */
public final class CalendarProgramArgumentsParser {
    /**
     * Parses the program arguments by creating a hashmap and saving each keyword.
     */
    public static HashMap<String, String> parseArguments(String[] args) {
        validateArguments(args);

        int length = CalendarProgramArgument.values().length;
        HashMap<String, String> arguments = new HashMap<>(length);

        boolean shouldUseGui = args[0].startsWith(CalendarProgramArgument.USE_GUI.getKeyWord());
        int offset = shouldUseGui ? 0 : 1;

        // Make sure to account for the offset otherwise an array out of bounds is thrown
        for (int i = 0; i < length - offset; i++) {
            CalendarProgramArgument argument = CalendarProgramArgument.values()[i + offset];
            arguments.put(argument.getKeyWord(), args[i]);
        }

        return arguments;
    }

    /**
     * Validates the incoming program arguments.
     */
    private static void validateArguments(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Example usage: java -jar exporter.jar 2025-08-01 2025-09-01 export.ics");
        }
    }
}