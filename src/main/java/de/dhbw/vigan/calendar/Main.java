package de.dhbw.vigan.calendar;

import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.handler.CalendarProgramArgument;
import de.dhbw.vigan.calendar.core.handler.ICalendarHandler;
import de.dhbw.vigan.calendar.core.handler.impl.CliCalendarHandler;
import de.dhbw.vigan.calendar.core.handler.impl.GuiCalendarHandler;
import de.dhbw.vigan.calendar.core.services.GoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.IGoogleCalendarService;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the main application entry.
 */
public class Main {

    /**
     * Starts the main application using a {@link ICalendarHandler}.
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        HashMap<String, String> arguments = loadArguments(args);
        CalendarOptions options = new CalendarOptions().load(arguments);
        IGoogleCalendarService googleCalendarService = new GoogleCalendarService();

        // If requested by the user, use the given GUI calendar handler otherwise the default CLI handler
        ICalendarHandler calendarHandler;
        if (options.UseGui) {
            calendarHandler = new GuiCalendarHandler(logger, googleCalendarService);
        } else {
            calendarHandler = new CliCalendarHandler(logger, googleCalendarService);
        }

        logger.log(Level.INFO, options.toString());

        calendarHandler.handle(options);
    }

    /**
     * Loads the program arguments by creating a hashmap and saving each keyword.
     */
    private static HashMap<String, String> loadArguments(String[] args) {
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
            throw new IllegalArgumentException("Example usage: java -jar calendar.jar 2025-08-01 2025-09-01 export.ics");
        }
    }
}