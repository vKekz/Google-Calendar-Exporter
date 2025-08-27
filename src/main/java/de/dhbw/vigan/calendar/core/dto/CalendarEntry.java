package de.dhbw.vigan.calendar.core.dto;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

/**
 * Represents a calendar entry.
 */
public record CalendarEntry(
        String summary,
        EventDateTime startDateTime,
        EventDateTime endDateTime,
        DateTime createdDateTime,
        String id,
        String description,
        DateTime lastModifiedTime,
        int sequence,
        String status,
        String transparency) {}