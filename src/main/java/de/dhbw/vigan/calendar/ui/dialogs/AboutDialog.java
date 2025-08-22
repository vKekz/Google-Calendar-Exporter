package de.dhbw.vigan.calendar.ui.dialogs;

import javax.swing.*;

public class AboutDialog extends JDialog {
    public AboutDialog() {
        setTitle("About");
        setSize(256, 128);
        setLocationRelativeTo(null);

        JLabel aboutLabel = new JLabel("<html>Developer: Vigan Veliu<br>Version: 1.0</html>", SwingConstants.CENTER);
        add(aboutLabel);
    }
}