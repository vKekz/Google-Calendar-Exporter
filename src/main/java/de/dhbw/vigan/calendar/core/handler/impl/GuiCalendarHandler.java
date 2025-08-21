package de.dhbw.vigan.calendar.core.handler.impl;

import de.dhbw.vigan.calendar.core.handler.CalendarOptions;
import de.dhbw.vigan.calendar.core.handler.ICalendarHandler;
import de.dhbw.vigan.calendar.core.services.IGoogleCalendarService;

import java.util.logging.Logger;

public class GuiCalendarHandler implements ICalendarHandler {
    private final Logger logger;
    private final IGoogleCalendarService googleCalendarService;

    public GuiCalendarHandler(Logger logger, IGoogleCalendarService googleCalendarService) {
        this.logger = logger;
        this.googleCalendarService = googleCalendarService;
    }

    @Override
    public void handle(CalendarOptions options) {
    }
}
