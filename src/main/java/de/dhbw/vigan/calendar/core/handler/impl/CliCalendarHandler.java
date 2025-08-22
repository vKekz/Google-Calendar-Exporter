package de.dhbw.vigan.calendar.core.handler.impl;

import de.dhbw.vigan.calendar.core.dto.CalendarEntry;
import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.handler.ICalendarHandler;
import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Represents the default CLI calendar handler.
 * <p>
 * This handler will read the events from the primary calendar and export it to the given file.
 */
public record CliCalendarHandler(Logger logger, IGoogleCalendarService googleCalendarService, ICalendarExportService exportService) implements ICalendarHandler {
    @Override
    public void handle(CalendarOptions options) {
        List<CalendarEntry> entries = googleCalendarService.getCalendarEntries("primary", options.StartDate, options.EndDate);
        exportService.Export(entries, options.ExportFileName);
    }
}