package de.dhbw.vigan.calendar.ui.menu;

import de.dhbw.vigan.calendar.ui.menu.items.ApplicationMenu;
import de.dhbw.vigan.calendar.ui.menu.items.FileMenu;
import de.dhbw.vigan.calendar.ui.menu.items.HelpMenu;

import javax.swing.*;

/**
 * Represents the UI menu bar.
 */
public class MenuBar extends JMenuBar {
    public MenuBar() {
        add(new FileMenu());
        add(new ApplicationMenu());
        add(new HelpMenu());
    }
}