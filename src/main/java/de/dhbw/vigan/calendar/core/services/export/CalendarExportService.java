package de.dhbw.vigan.calendar.core.services.export;

import de.dhbw.vigan.calendar.core.dto.CalendarEntry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @see ICalendarExportService
 */
public record CalendarExportService(Logger logger) implements ICalendarExportService {
    @Override
    public void Export(List<CalendarEntry> entries, String exportFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BEGIN:VCALENDAR\n");
        stringBuilder.append("VERSION:2.0\n");
        stringBuilder.append("PRODID:-//DHBW//Google Calendar Exporter//EN\n");

        for (CalendarEntry entry : entries) {
            stringBuilder.append("BEGIN:VEVENT\n");
            stringBuilder.append("DTSTART:").append(entry.startDateTime()).append("\n");
            stringBuilder.append("DTEND:").append(entry.endDateTime()).append("\n");
            stringBuilder.append("UID:").append(entry.id()).append("\n");
            stringBuilder.append("CREATED:").append(entry.createdDateTime()).append("\n");
            stringBuilder.append("DESCRIPTION:").append(entry.description()).append("\n");
            stringBuilder.append("LAST-MODIFIED:").append(entry.lastModifiedTime()).append("\n");
            stringBuilder.append("SEQUENCE:").append(entry.sequence()).append("\n");
            stringBuilder.append("STATUS:").append(entry.status()).append("\n");
            stringBuilder.append("SUMMARY:").append(entry.summary()).append("\n");
            stringBuilder.append("TRANSP:").append(entry.transparency()).append("\n");
            stringBuilder.append("END:VEVENT\n");
        }

        stringBuilder.append("END:VCALENDAR");

        File exportFile = new File(exportFileName);
        File parentDir = exportFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                logger.info("Created directory: " + parentDir.getAbsolutePath());
            } else {
                logger.warning("Could not create directory: " + parentDir.getAbsolutePath());
            }
        }

        try (FileWriter fileWriter = new FileWriter(exportFile)) {
            fileWriter.write(stringBuilder.toString());
            logger.info("Exported " + entries.size() + " entries to " + exportFileName);
        } catch (IOException e) {
            logger.warning("Could not export calendar to file " + exportFileName);
        }
    }
}