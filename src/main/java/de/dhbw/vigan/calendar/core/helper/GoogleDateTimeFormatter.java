package de.dhbw.vigan.calendar.core.helper;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a helper class that is used to format google date-times.
 */
public final class GoogleDateTimeFormatter {
    /**
     * Formats an {@link EventDateTime} by checking if it is a DateTime or Date.
     */
    public static String formatEventDateTime(EventDateTime eventDateTime) {
        // Handle dates
        if (eventDateTime.getDate() != null) {
            LocalDate localDate = LocalDate.parse(eventDateTime.getDate().toStringRfc3339());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            return localDate.format(formatter);
        }

        // Handle date times
        if (eventDateTime.getDateTime() != null) {
            return formatDateTime(eventDateTime.getDateTime());
        }

        return null;
    }

    /**
     * Formats a {@link DateTime}.
     */
    public static String formatDateTime(DateTime dateTime) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTime.toStringRfc3339());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return offsetDateTime.format(formatter);
    }
}
