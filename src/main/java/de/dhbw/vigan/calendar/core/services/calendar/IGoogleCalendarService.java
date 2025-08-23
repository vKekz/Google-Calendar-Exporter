package de.dhbw.vigan.calendar.core.services.calendar;

import de.dhbw.vigan.calendar.core.dto.CalendarEntry;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents the service that is used to communicate with the Google Calendar API.
 */
public interface IGoogleCalendarService {
    /**
     * Returns a list of {@link CalendarEntry} from the given calendar ID. Will filter for the given time interval.
     */
    List<CalendarEntry> getCalendarEntries(String calenderId, LocalDate startDate, LocalDate endDate);
    /**
     * Returns the ID of the given calendar.
     */
    String getCalenderIdByName(String calenderName) throws Exception;
    /**
     * Returns the list of calendar IDs owned by the user (determined by credentials.json).
     */
    List<String> getCalendarIds() throws Exception;
}