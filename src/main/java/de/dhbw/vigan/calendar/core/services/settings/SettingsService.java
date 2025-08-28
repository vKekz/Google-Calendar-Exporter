package de.dhbw.vigan.calendar.core.services.settings;

public class SettingsService implements ISettingsService {
    private final CalendarSettings calendarSettings;

    public SettingsService() {
        this.calendarSettings = new CalendarSettings();
    }

    @Override
    public void saveSettings(CalendarSettings calendarSettings) {
        this.calendarSettings.calendarUrl = calendarSettings.calendarUrl;
        this.calendarSettings.exportFileName = calendarSettings.exportFileName;
        this.calendarSettings.startDate = calendarSettings.startDate;
        this.calendarSettings.endDate = calendarSettings.endDate;
    }

    @Override
    public CalendarSettings getSettings() {
        return calendarSettings;
    }
}
