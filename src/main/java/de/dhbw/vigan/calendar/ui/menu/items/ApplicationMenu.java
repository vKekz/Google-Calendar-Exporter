package de.dhbw.vigan.calendar.ui.menu.items;

import de.dhbw.vigan.calendar.ui.ApplicationUi;
import de.dhbw.vigan.calendar.ui.dialogs.SettingsDialog;

import javax.swing.*;

public class ApplicationMenu extends JMenu {
    public ApplicationMenu(ApplicationUi applicationUi) {
        setText("Application");

        JMenuItem settingsItem = new  JMenuItem("Settings");
        SettingsDialog settingsDialog = new SettingsDialog(applicationUi);
        settingsItem.addActionListener(_ -> settingsDialog.setVisible(true));

        add(settingsItem);
    }
}