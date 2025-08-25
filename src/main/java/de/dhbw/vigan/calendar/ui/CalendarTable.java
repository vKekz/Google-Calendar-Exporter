package de.dhbw.vigan.calendar.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CalendarTable extends JTable {
    public CalendarTable() {
        String[] columns = { "Ereignis", "Von", "Bis" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        setModel(model);

        // TODO: Fill data
    }
}
