package de.dhbw.vigan.calendar.ui.menu.items;

import de.dhbw.vigan.calendar.ui.ApplicationUi;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Represents the file menu bar item.
 */
public class FileMenu extends JMenu {
    public FileMenu(ApplicationUi applicationUi) {
        setText("File");

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(_ -> saveAction(applicationUi));

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(_ -> {
            applicationUi.dispatchEvent(new WindowEvent(applicationUi, WindowEvent.WINDOW_CLOSING));
        });

        add(saveItem);
        add(exitItem);
    }

    private void saveAction(ApplicationUi applicationUi) {
        applicationUi.export();
    }
}