package de.dhbw.vigan.calendar.ui.dialogs;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {
    public SettingsDialog() {
        setTitle("Settings");
        setSize(256, 128);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
    }
}