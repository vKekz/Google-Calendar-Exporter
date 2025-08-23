package de.dhbw.vigan.calendar.ui;

import de.dhbw.vigan.calendar.Main;
import de.dhbw.vigan.calendar.core.services.calendar.IGoogleCalendarService;
import de.dhbw.vigan.calendar.core.services.export.ICalendarExportService;
import de.dhbw.vigan.calendar.ui.menu.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

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
        setIcon();
        setJMenuBar(new MenuBar(this));
    }

    private void setIcon() {
        try (InputStream stream = Main.class.getResourceAsStream("/images/icon.png")) {
            if (stream != null) {
                ImageIcon icon = new ImageIcon(stream.readAllBytes());
                setIconImage(icon.getImage());
            }
        } catch (IOException _) {
        }
    }
}