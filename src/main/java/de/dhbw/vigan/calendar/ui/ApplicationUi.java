package de.dhbw.vigan.calendar.ui;

import de.dhbw.vigan.calendar.Main;
import de.dhbw.vigan.calendar.core.dto.CalendarEntry;
import de.dhbw.vigan.calendar.core.handler.CalendarConstants;
import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;
import de.dhbw.vigan.calendar.core.services.settings.CalendarSettings;
import de.dhbw.vigan.calendar.ui.menu.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents the application UI window.
 */
public class ApplicationUi extends JFrame {
    private final Logger logger;
    private final IGoogleCalendarService googleCalendarService;

    private CalendarTable calendarTable;

    public CalendarOptions options;
    public CalendarSettings settings;

    public ApplicationUi(Logger logger, IGoogleCalendarService googleCalendarService, ICalendarExportService exportService, CalendarOptions options) {
        this.logger = logger;
        this.googleCalendarService = googleCalendarService;

        this.options = options;
        this.settings = new CalendarSettings(options);

        initialize();
        loadEvents();
    }

    public void open() {
        setVisible(true);
    }

    public void saveSettings() {

    }

    public void loadEvents() {
        // List<CalendarEntry> entries = googleCalendarService.getCalendarEntries(settings.calendarUrl, settings.startDate, settings.endDate);
        List<CalendarEntry> entries = googleCalendarService.getCalendarEntries(
                CalendarConstants.PRIMARY_CALENDAR_ID,
                options.startDate,
                options.endDate);
        for (CalendarEntry entry : entries) {
            calendarTable.addEntry(entry);
        }
    }

    public void export() {

    }

    private void initialize() {
        logger.info("Initializing Application UI...");

        setTitle("Google Calendar Exporter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 540);
        setLocationRelativeTo(null);
        setIcon();
        setJMenuBar(new MenuBar(this));

        calendarTable = new CalendarTable();
        add(new JScrollPane(calendarTable), BorderLayout.CENTER);
    }

    private void setIcon() {
        try (InputStream stream = Main.class.getResourceAsStream("/images/icon.png")) {
            if (stream == null) {
                return;
            }

            logger.info("Setting up Icon...");
            ImageIcon icon = new ImageIcon(stream.readAllBytes());
            setIconImage(icon.getImage());
        } catch (IOException e) {
            logger.warning("Could not load icon image:  " + e.getMessage());
        }
    }
}