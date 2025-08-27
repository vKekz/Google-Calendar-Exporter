package de.dhbw.vigan.calendar.core.services.settings;

import de.dhbw.vigan.calendar.core.handler.CalendarOptions;

import java.time.LocalDate;

public class CalendarSettings {
    public String calendarUrl;
    public String exportFileName;
    public LocalDate startDate;
    public LocalDate endDate;

    public CalendarSettings(CalendarOptions options) {
        exportFileName = options.exportFileName;
    }
}