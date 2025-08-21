package de.dhbw.vigan.calendar.services;

import com.google.api.services.calendar.model.Events;

public interface IGoogleCalendarService {
    Events getUpcomingEvents();
}