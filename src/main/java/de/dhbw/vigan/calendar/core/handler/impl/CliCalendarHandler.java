package de.dhbw.vigan.calendar.core.handler.impl;

import de.dhbw.vigan.calendar.core.dto.CalendarEntry;
import de.dhbw.vigan.calendar.core.handler.CalendarConstants;
import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.handler.ICalendarHandler;
import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;

import java.util.List;

/**
 * Represents the default CLI calendar handler.
 * <p>
 * This handler will read the events from the primary calendar and export it to the given file.
 */
public record CliCalendarHandler(IGoogleCalendarService googleCalendarService, ICalendarExportService exportService) implements ICalendarHandler {
    @Override
    public void handle(CalendarOptions options) {
        List<CalendarEntry> entries = googleCalendarService.getCalendarEntries(
                CalendarConstants.PRIMARY_CALENDAR_ID,
                options.startDate,
                options.endDate);
        exportService.Export(entries, options.exportFileName);
    }
}