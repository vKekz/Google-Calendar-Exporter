package de.dhbw.vigan.calendar;

import de.dhbw.vigan.calendar.core.arguments.CalendarProgramArgumentsParser;
import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.handler.ICalendarHandler;
import de.dhbw.vigan.calendar.core.handler.impl.CliCalendarHandler;
import de.dhbw.vigan.calendar.core.handler.impl.GuiCalendarHandler;
import de.dhbw.vigan.calendar.core.services.calendar.GoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.credentials.GoogleCredentialsService;
import de.dhbw.vigan.calendar.core.services.export.CalendarExportService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;
import de.dhbw.vigan.calendar.ui.ApplicationUi;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Represents the application startup.
 */
public record Startup(String[] args) {
    /**
     * Initializes and runs the application.
     */
    public void Run() {
        Logger logger = Logger.getLogger(Main.class.getName());
        HashMap<String, String> arguments = CalendarProgramArgumentsParser.parseArguments(args);
        CalendarOptions options = new CalendarOptions().load(arguments);

        IGoogleCalendarService googleCalendarService;
        try {
            googleCalendarService = new GoogleCalendarService(new GoogleCredentialsService());
        } catch (Exception e) {
            throw new RuntimeException("Could not create Google Calendar Service", e);
        }

        ICalendarExportService exportService = new CalendarExportService(logger);
        ICalendarHandler calendarHandler;

        if (options.UseGui) {
            // If requested by the user, use the given GUI calendar handler otherwise the default CLI handler
            ApplicationUi ui = new ApplicationUi(googleCalendarService, exportService);
            calendarHandler = new GuiCalendarHandler(logger, ui);
        } else {
            calendarHandler = new CliCalendarHandler(logger, googleCalendarService, exportService);
        }

        logger.info("Supplied options: " + options);
        logger.info("Handling " + (options.UseGui ? "GUI" : "CLI") + " version");

        calendarHandler.handle(options);
    }
}