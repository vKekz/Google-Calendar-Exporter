package de.dhbw.vigan.calendar.core.handler;

/**
 * Represents a calendar handler.
 */
public interface ICalendarHandler {
    /**
     * Handles the given handler using the supplied start options.
     */
    void handle(CalendarOptions options);
}