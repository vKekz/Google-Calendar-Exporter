package de.dhbw.vigan.calendar.ui;

import de.dhbw.vigan.calendar.Main;
import de.dhbw.vigan.calendar.core.dto.CalendarEntry;
import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;
import de.dhbw.vigan.calendar.core.services.settings.CalendarSettings;
import de.dhbw.vigan.calendar.core.services.settings.ISettingsService;
import de.dhbw.vigan.calendar.ui.menu.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents the application UI window.
 */
public class ApplicationUi extends JFrame {
    public final IGoogleCalendarService googleCalendarService;
    public final ISettingsService settingsService;
    public final ICalendarExportService calendarExportService;

    private final Logger logger;
    private CalendarTable calendarTable;

    public ApplicationUi(Logger logger,
                         IGoogleCalendarService googleCalendarService,
                         ICalendarExportService exportService,
                         ISettingsService settingsService) {
        this.logger = logger;
        this.googleCalendarService = googleCalendarService;
        this.settingsService = settingsService;
        this.calendarExportService = exportService;

        initialize();
    }

    public void open() {
        setVisible(true);
    }

    public void saveSettings(CalendarSettings newSettings) {
        settingsService.saveSettings(newSettings);
        loadEvents(newSettings);
    }

    public void loadEvents(CalendarSettings settings) {
        calendarTable.clearRows();

        List<CalendarEntry> entries = googleCalendarService.getCalendarEntries(
                settings.calendarUrl,
                settings.startDate,
                settings.endDate);
        for (CalendarEntry entry : entries) {
            calendarTable.addEntry(entry);
        }
    }

    public void export() {
        calendarExportService.Export(googleCalendarService.getMostRecentEntries(), settingsService.getSettings().exportFileName);
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleExit();
            }
        });
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

    private void handleExit() {
        logger.info("Exiting Application UI...");
        dispose();
    }
}