package de.dhbw.vigan.calendar.ui.menu.items;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the file menu bar item.
 */
public class FileMenu extends JMenu {
    public FileMenu() {
        setText("File");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        saveItem.addActionListener(this::saveAction);
        exitItem.addActionListener(_ -> System.exit(0));

        add(saveItem);
        add(exitItem);
    }

    private void saveAction(ActionEvent event) {

    }
}