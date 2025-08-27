package de.dhbw.vigan.calendar.ui;

import de.dhbw.vigan.calendar.core.handler.CalendarOptions;

public class CalendarSettings {
    public String calendarUrl;
    public String exportFileName;

    public CalendarSettings(CalendarOptions options) {
        exportFileName = options.exportFileName;
    }
}