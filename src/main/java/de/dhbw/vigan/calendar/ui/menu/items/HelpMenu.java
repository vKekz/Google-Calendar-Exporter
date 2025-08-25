package de.dhbw.vigan.calendar.ui.menu.items;

import de.dhbw.vigan.calendar.ui.ApplicationUi;
import de.dhbw.vigan.calendar.ui.dialogs.AboutDialog;

import javax.swing.*;

/**
 * Represents the help menu bar item.
 */
public class HelpMenu extends JMenu {
    public HelpMenu(ApplicationUi applicationUi) {
        setText("Help");

        JMenuItem settingsItem = new JMenuItem("About");
        AboutDialog aboutDialog = new AboutDialog(applicationUi);
        settingsItem.addActionListener(_ -> aboutDialog.setVisible(true));

        add(settingsItem);
    }
}