package de.dhbw.vigan.calendar.ui;

import de.dhbw.vigan.calendar.core.dto.CalendarEntry;
import de.dhbw.vigan.calendar.core.helper.GoogleDateTimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CalendarTable extends JTable {
    private final DefaultTableModel model;

    public CalendarTable() {
        String[] columns = { "Ereignis", "Von", "Bis" };
        model = new DefaultTableModel(columns, 0);
        setModel(model);

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
    }

    public void addEntry(CalendarEntry entry) {
        String formatedStartTime = GoogleDateTimeFormatter.formatEventDateTime(entry.startDateTime());
        String formatedEndTime = GoogleDateTimeFormatter.formatEventDateTime(entry.endDateTime());

        model.addRow(new Object[] { entry.summary(), formatedStartTime, formatedEndTime });
    }
}
