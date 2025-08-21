package de.dhbw.vigan.calendar.core.services;

import com.google.api.services.calendar.model.Events;

/**
 * Represents the service that is used to communicate with the Google Calendar API.
 */
public interface IGoogleCalendarService {
    Events getUpcomingEvents();
}