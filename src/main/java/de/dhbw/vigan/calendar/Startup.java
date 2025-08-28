package de.dhbw.vigan.calendar;

import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.handler.CalendarOptionsParser;
import de.dhbw.vigan.calendar.core.handler.ICalendarHandler;
import de.dhbw.vigan.calendar.core.handler.impl.CliCalendarHandler;
import de.dhbw.vigan.calendar.core.handler.impl.GuiCalendarHandler;
import de.dhbw.vigan.calendar.core.services.calendar.GoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.credentials.GoogleCredentialsService;
import de.dhbw.vigan.calendar.core.services.export.CalendarExportService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;
import de.dhbw.vigan.calendar.core.services.settings.ISettingsService;
import de.dhbw.vigan.calendar.core.services.settings.SettingsService;
import de.dhbw.vigan.calendar.ui.ApplicationUi;

import java.util.logging.Logger;

/**
 * Represents the application startup.
 */
public class Startup {
    private final String[] args;

    public Startup(String[] args) {
        this.args = args;
    }

    /**
     * Initializes and runs the application.
     */
    public void Run() {
        Logger logger = Logger.getLogger(Main.class.getName());
        CalendarOptions options = CalendarOptionsParser.parse(args);
        logger.info("Supplied options: " + options);

        IGoogleCalendarService googleCalendarService = new GoogleCalendarService(logger, new GoogleCredentialsService());
        ICalendarExportService exportService = new CalendarExportService(logger);
        ICalendarHandler calendarHandler;
        if (options.useGui) {
            // If requested by the user, use the given GUI calendar handler otherwise the default CLI handler
            ISettingsService settingsService = new SettingsService();
            ApplicationUi ui = new ApplicationUi(logger, googleCalendarService, exportService, settingsService);
            calendarHandler = new GuiCalendarHandler(logger, ui);
            logger.info("Handling GUI ...");
        } else {
            calendarHandler = new CliCalendarHandler(googleCalendarService, exportService);
            logger.info("Handling CLI ...");
        }

        calendarHandler.handle(options);
    }
}