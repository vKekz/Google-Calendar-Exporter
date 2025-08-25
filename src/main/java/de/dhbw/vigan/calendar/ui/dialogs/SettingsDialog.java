package de.dhbw.vigan.calendar.ui.dialogs;

import de.dhbw.vigan.calendar.ui.ApplicationUi;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the Settings window.
 */
public class SettingsDialog extends JDialog {
    public SettingsDialog(ApplicationUi applicationUi) {
        setTitle("Settings");
        setSize(256, 128);
        setResizable(false);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setIconImage(applicationUi.getIconImage());

        // TODO: Fill with settings
        // URL, FROM, TO...
    }
}