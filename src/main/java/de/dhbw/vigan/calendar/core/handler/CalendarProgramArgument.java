package de.dhbw.vigan.calendar.core.handler;

/**
 * Represents the type of program argument.
 */
public enum CalendarProgramArgument {
    /**
     * Represents the value whether the program should use a GUI or not.
     */
    USE_GUI("gui"),

    /**
     * Represents the minimum date for each calendar entry.
     */
    START_DATE("startDate"),

    /**
     * Represents the maximum date for each calendar entry.
     */
    END_DATE("endDate"),

    /**
     * Represents the export file name.
     */
    EXPORT_FILE_NAME("exportFileName");

    private final String keyWord;

    CalendarProgramArgument(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
