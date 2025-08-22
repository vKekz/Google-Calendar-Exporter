package de.dhbw.vigan.calendar.ui;

import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;
import de.dhbw.vigan.calendar.ui.menu.MenuBar;

import javax.swing.*;

/**
 * Represents the application UI window.
 */
public class ApplicationUi extends JFrame {
    public ApplicationUi(IGoogleCalendarService googleCalendarService, ICalendarExportService exportService) {
        initialize();
    }

    public void open() {
        setVisible(true);
    }

    private void initialize() {
        setTitle("Google Calendar Exporter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 540);
        setLocationRelativeTo(null);

        setJMenuBar(new MenuBar());
    }
}