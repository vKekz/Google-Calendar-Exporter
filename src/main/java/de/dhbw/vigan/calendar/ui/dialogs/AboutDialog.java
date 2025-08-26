package de.dhbw.vigan.calendar.ui.dialogs;

import de.dhbw.vigan.calendar.ui.ApplicationUi;

import javax.swing.*;

/**
 * Represents the About window.
 */
public class AboutDialog extends JDialog {
    public AboutDialog(ApplicationUi applicationUi) {
        super(applicationUi, "About", true);

        setSize(300, 128);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(applicationUi.getIconImage());

        JLabel aboutLabel = new JLabel("<html>Developer: Vigan Veliu<br>Version: 1.0<br>https://www.svgrepo.com/svg/49397/calendar</html>", SwingConstants.CENTER);
        add(aboutLabel);

        // TODO: Make prettier
    }
}