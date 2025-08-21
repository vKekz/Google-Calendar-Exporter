package de.dhbw.vigan.calendar;

import com.google.api.services.calendar.model.Event;
import de.dhbw.vigan.calendar.services.GoogleCalendarService;
import de.dhbw.vigan.calendar.services.IGoogleCalendarService;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final HashMap<String, String> ARGUMENTS = new HashMap<>(3);

    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Missing arguments");
            System.out.println("Syntax: java -jar calendar.jar --startDate 2025-08-01 --endDate 2025-09-01 --calendarFile export.ics");
            return;
        }

        for (int i = 0; i < args.length; i+=2) {
            String keyWord = args[i].substring(2);
            ARGUMENTS.put(keyWord, args[i+1]);
        }

        CalendarOptions options = new CalendarOptions();
        options.StartDate = ARGUMENTS.get(CalendarOptions.START_DATE_KEYWORD);
        options.EndDate = ARGUMENTS.get(CalendarOptions.END_DATE_KEYWORD);
        options.CalendarFileName = ARGUMENTS.get(CalendarOptions.CALENDAR_FILE_NAME_KEYWORD);

        System.out.println("Options: " + options);

        IGoogleCalendarService googleCalendarService = new GoogleCalendarService();
        List<Event> events = googleCalendarService.getUpcomingEvents().getItems();

        if (events.isEmpty()) {
            System.out.println("No events found");
            return;
        }

        for (Event event : events) {
            System.out.println(event.getStart());
        }
    }
}