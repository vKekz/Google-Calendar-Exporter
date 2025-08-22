package de.dhbw.vigan.calendar.core.services.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import de.dhbw.vigan.calendar.core.dto.CalendarEntry;
import de.dhbw.vigan.calendar.core.services.credentials.IGoogleCredentialsService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @see IGoogleCalendarService
 */
public class GoogleCalendarService implements IGoogleCalendarService {
    private static final String APP_NAME = "Google Calendar";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final Calendar calendar;

    public GoogleCalendarService(IGoogleCredentialsService credentialsService) throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = credentialsService.getCredentials(httpTransport);

        this.calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APP_NAME)
                .build();

        // TODO: Pre-fetch calendars?
    }

    @Override
    public List<CalendarEntry> getCalendarEntries(String calenderId, LocalDate startDate, LocalDate endDate) {
        try {
            // Datetime needs a specific time
            LocalTime now = LocalTime.now();
            DateTime start = new DateTime(startDate.atTime(now).toString());
            DateTime end = new DateTime(endDate.atTime(now).toString());

            List<Event> events = calendar.events()
                    .list(calenderId)
                    .setTimeMin(start)
                    .setTimeMax(end)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute()
                    .getItems();

            // Map events to custom calendar entry DTO
            return events
                    .stream()
                    .map(event -> new CalendarEntry(
                            event.getSummary(),
                            event.getStart().toString(),
                            event.getEnd().toString(),
                            event.getCreated().toStringRfc3339(),
                            event.getICalUID(),
                            event.getDescription(),
                            event.getUpdated().toStringRfc3339(),
                            event.getSequence(),
                            event.getStatus(),
                            event.getTransparency()))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCalenderIdByName(String calenderName) throws Exception {
        CalendarList calendarList = calendar.calendarList().list().execute();
        List<CalendarListEntry> calendars = calendarList.getItems();

        for (CalendarListEntry calendar : calendars) {
            if (calendar.getSummary().equalsIgnoreCase(calenderName)) {
                return calendar.getId();
            }
        }

        return null;
    }

    @Override
    public List<String> getCalendarIds() {
        return List.of();
    }
}