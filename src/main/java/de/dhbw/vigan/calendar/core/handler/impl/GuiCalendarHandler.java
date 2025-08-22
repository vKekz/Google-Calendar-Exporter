package de.dhbw.vigan.calendar.core.handler.impl;

import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.handler.ICalendarHandler;
import de.dhbw.vigan.calendar.ui.ApplicationUi;

import java.util.logging.Logger;

/**
 * Represents the GUI calendar handler.
 * <p>
 * This handler will open a GUI for the user.
 */
public record GuiCalendarHandler(Logger logger, ApplicationUi ui) implements ICalendarHandler {
    @Override
    public void handle(CalendarOptions options) {
        ui.open();
    }
}