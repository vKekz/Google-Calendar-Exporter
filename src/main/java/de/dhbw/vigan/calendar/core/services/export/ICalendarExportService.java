package de.dhbw.vigan.calendar.core.services.export;

import de.dhbw.vigan.calendar.core.dto.CalendarEntry;

import java.util.List;

/**
 * Represents the service that is used to export a calendar.
 */
public interface ICalendarExportService {
    /**
     * Exports the list of {@link CalendarEntry}.
     */
    void Export(List<CalendarEntry> entries, String exportFileName);
}