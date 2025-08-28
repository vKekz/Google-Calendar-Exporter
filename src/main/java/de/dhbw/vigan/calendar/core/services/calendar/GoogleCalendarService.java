package de.dhbw.vigan.calendar.core.services.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import de.dhbw.vigan.calendar.core.dto.CalendarEntry;
import de.dhbw.vigan.calendar.core.services.credentials.IGoogleCredentialsService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @see IGoogleCalendarService
 */
public class GoogleCalendarService implements IGoogleCalendarService {
    private static final String APP_NAME = "Google Calendar";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final Logger logger;
    private final Calendar calendar;

    private List<CalendarEntry> mostRecentEntries = new ArrayList<>();

    public GoogleCalendarService(Logger logger, IGoogleCredentialsService credentialsService) {
        this.logger = logger;

        try {
            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            Credential credential = credentialsService.getCredentials(httpTransport);

            this.calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                                        .setApplicationName(APP_NAME)
                                        .build();
        } catch (Exception e) {
            logger.severe("Error while creating Google Calendar service: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CalendarEntry> getCalendarEntries(String calenderId, LocalDate startDate, LocalDate endDate) {
        try {
            logger.info("Getting calendar entries for calendar ID: " + calenderId);

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
            List<CalendarEntry> entries = events
                    .stream()
                    .map(event -> new CalendarEntry(
                            event.getSummary(),
                            event.getStart(),
                            event.getEnd(),
                            event.getCreated(),
                            event.getICalUID(),
                            event.getDescription(),
                            event.getUpdated(),
                            event.getSequence(),
                            event.getStatus(),
                            event.getTransparency()))
                    .toList();

            mostRecentEntries = entries;

            return entries;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CalendarEntry> getMostRecentEntries() {
        return mostRecentEntries;
    }
}