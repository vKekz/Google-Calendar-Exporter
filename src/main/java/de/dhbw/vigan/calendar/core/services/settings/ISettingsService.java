package de.dhbw.vigan.calendar.core.services.settings;

public interface ISettingsService {
    void saveSettings(CalendarSettings calendarSettings);
    CalendarSettings getSettings();
}
