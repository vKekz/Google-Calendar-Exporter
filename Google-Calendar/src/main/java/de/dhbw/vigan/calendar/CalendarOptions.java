package de.dhbw.vigan.calendar;

public class CalendarOptions {
    public static final String START_DATE_KEYWORD = "startDate";
    public static final String END_DATE_KEYWORD = "endDate";
    public static final String CALENDAR_FILE_NAME_KEYWORD = "calendarFile";

    public String StartDate;
    public String EndDate;
    public String CalendarFileName;

    @Override
    public String toString() {
        return "StartDate: " + StartDate + ", EndDate: " + EndDate + ", File: " + CalendarFileName;
    }
}