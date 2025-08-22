package de.dhbw.vigan.calendar.core.dto;

/**
 * Represents a calendar entry.
 */
public record CalendarEntry(
        String summary,
        String startDateTime,
        String endDateTime,
        String createdDateTime,
        String id,
        String description,
        String lastModifiedTime,
        int sequence,
        String status,
        String transparency) {}