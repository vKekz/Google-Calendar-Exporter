package de.dhbw.vigan.calendar.ui.dialogs;

import de.dhbw.vigan.calendar.ui.ApplicationUi;

import javax.swing.*;

/**
 * Represents the About window.
 */
public class AboutDialog extends JDialog {
    public AboutDialog(ApplicationUi applicationUi) {
        super(applicationUi, "About Google Calendar Exporter", true);

        setSize(320, 160);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(applicationUi.getIconImage());

        String html = """
                      <html>
                          <p>Developed by Vigan Veliu</p>
                          <p>Version: 1.0</p>
                          <hr>
                          Calendar icon:
                          <p>https://www.svgrepo.com/svg/49397/calendar</p>
                      </html>
                      """;
        JLabel aboutLabel = new JLabel(html, SwingConstants.CENTER);
        add(aboutLabel);
    }
}