package de.dhbw.vigan.calendar.core.handler;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents the options that can be supplied via program arguments.
 */
public class CalendarOptions {
    public LocalDate startDate;
    public LocalDate endDate;
    public String exportFileName;
    public boolean useGui;

    public CalendarOptions(HashMap<String, String> arguments) {
        startDate = LocalDate.parse(arguments.get(CalendarProgramArgument.START_DATE.getKeyWord()));
        endDate = LocalDate.parse(arguments.get(CalendarProgramArgument.END_DATE.getKeyWord()));
        exportFileName = arguments.get(CalendarProgramArgument.EXPORT_FILE_NAME.getKeyWord());
        useGui = arguments.containsKey(CalendarProgramArgument.USE_GUI.getKeyWord());
    }

    @Override
    public String toString() {
        return "Gui: " + useGui + " | StartDate: " + startDate + " | EndDate: " + endDate + " | File: " + exportFileName;
    }
}