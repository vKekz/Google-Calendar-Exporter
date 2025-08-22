package de.dhbw.vigan.calendar.ui.menu.items;

import javax.swing.*;

/**
 * Represents the file menu item.
 */
public class FileMenu extends JMenu {

    public FileMenu() {
        setText("File");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(_ -> System.exit(0));

        add(saveItem);
        add(exitItem);
    }
}