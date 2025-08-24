package de.dhbw.vigan.calendar.core.handler;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents the options that can be supplied via program arguments.
 */
public class CalendarOptions {
    public LocalDate StartDate;
    public LocalDate EndDate;
    public String ExportFileName;
    public boolean UseGui;

    public CalendarOptions(HashMap<String, String> arguments) {
        StartDate = LocalDate.parse(arguments.get(CalendarProgramArgument.START_DATE.getKeyWord()));
        EndDate = LocalDate.parse(arguments.get(CalendarProgramArgument.END_DATE.getKeyWord()));
        ExportFileName = arguments.get(CalendarProgramArgument.EXPORT_FILE_NAME.getKeyWord());
        UseGui = arguments.containsKey(CalendarProgramArgument.USE_GUI.getKeyWord());
    }

    @Override
    public String toString() {
        return "Gui: " + UseGui + " | StartDate: " + StartDate + " | EndDate: " + EndDate + " | File: " + ExportFileName;
    }
}