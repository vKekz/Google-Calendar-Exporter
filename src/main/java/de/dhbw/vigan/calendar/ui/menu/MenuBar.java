package de.dhbw.vigan.calendar.ui.menu;

import de.dhbw.vigan.calendar.ui.ApplicationUi;
import de.dhbw.vigan.calendar.ui.menu.items.ApplicationMenu;
import de.dhbw.vigan.calendar.ui.menu.items.FileMenu;
import de.dhbw.vigan.calendar.ui.menu.items.HelpMenu;

import javax.swing.*;

/**
 * Represents the UI menu bar.
 */
public class MenuBar extends JMenuBar {
    public MenuBar(ApplicationUi applicationUi) {
        add(new FileMenu(applicationUi));
        add(new ApplicationMenu(applicationUi));
        add(new HelpMenu(applicationUi));
    }
}