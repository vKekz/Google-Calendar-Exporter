package de.dhbw.vigan.calendar.ui.menu.items;

import de.dhbw.vigan.calendar.ui.dialogs.AboutDialog;

import javax.swing.*;

public class HelpMenu extends JMenu {
    public HelpMenu() {
        setText("Help");

        JMenuItem settingsItem = new JMenuItem("About");
        AboutDialog aboutDialog = new AboutDialog();
        settingsItem.addActionListener(_ -> aboutDialog.setVisible(true));

        add(settingsItem);
    }
}