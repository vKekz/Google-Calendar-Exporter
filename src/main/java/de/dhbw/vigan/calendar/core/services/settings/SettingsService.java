package de.dhbw.vigan.calendar.core.services.settings;

public class SettingsService implements ISettingsService {
    private final CalendarSettings calendarSettings;

    public SettingsService(CalendarSettings calendarSettings) {
        this.calendarSettings = calendarSettings;
    }

    @Override
    public void saveSettings(CalendarSettings calendarSettings) {
        this.calendarSettings.calendarUrl = calendarSettings.calendarUrl;
        this.calendarSettings.exportFileName = calendarSettings.exportFileName;
    }

    @Override
    public CalendarSettings getSettings() {
        return calendarSettings;
    }
}
