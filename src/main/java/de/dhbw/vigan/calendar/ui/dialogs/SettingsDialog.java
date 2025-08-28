package de.dhbw.vigan.calendar.ui.dialogs;

import de.dhbw.vigan.calendar.core.services.settings.CalendarSettings;
import de.dhbw.vigan.calendar.ui.ApplicationUi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Represents the Settings window.
 */
public class SettingsDialog extends JDialog {
    private final CalendarSettings newSettings = new CalendarSettings();
    private final ApplicationUi applicationUi;

    private JTextField calendarUrl;
    private JTextField exportFile;
    private JSpinner startDate;
    private JSpinner endDate;

    public SettingsDialog(ApplicationUi applicationUi) {
        // This is needed so that the user is not able to select the main application until the settings windows is closed
        super(applicationUi, "Settings", true);

        this.applicationUi = applicationUi;

        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(applicationUi);
        setIconImage(applicationUi.getIconImage());

        setupSettingsPanel(applicationUi);
        setupButtonsPanel();

    }

    private void setupSettingsPanel(ApplicationUi applicationUi) {
        JPanel settingsPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        settingsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        CalendarSettings settings = applicationUi.settingsService.getSettings();

        settingsPanel.add(new JLabel("Calendar URL:"));
        calendarUrl = new JTextField(settings.calendarUrl);
        settingsPanel.add(calendarUrl);

        settingsPanel.add(new JLabel("Export file name:"));
        exportFile = new JTextField(settings.exportFileName);
        settingsPanel.add(exportFile);

        settingsPanel.add(new JLabel("Start date:"));
        startDate = new JSpinner(new SpinnerDateModel());
        if (settings.startDate != null) {
            startDate.setValue(parseLocalDate(settings.startDate));
        }
        settingsPanel.add(startDate);

        settingsPanel.add(new JLabel("End date:"));
        endDate = new JSpinner(new SpinnerDateModel());
        if (settings.endDate != null) {
            endDate.setValue(parseLocalDate(settings.endDate));
        }
        settingsPanel.add(endDate);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(settingsPanel, BorderLayout.CENTER);
    }

    private void setupButtonsPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(this::loadAction);
        buttonPanel.add(loadButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelAction);
        buttonPanel.add(cancelButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadAction(ActionEvent event) {
        saveSettings();
        setVisible(false);
    }

    private void cancelAction(ActionEvent event) {
        setVisible(false);
    }

    private void saveSettings() {
        newSettings.calendarUrl = calendarUrl.getText();
        newSettings.exportFileName = exportFile.getText();
        newSettings.startDate = parseSpinnerDateModel((SpinnerDateModel)startDate.getModel());
        newSettings.endDate = parseSpinnerDateModel((SpinnerDateModel)endDate.getModel());

        applicationUi.saveSettings(newSettings);
    }

    private LocalDate parseSpinnerDateModel(SpinnerDateModel dateModel) {
        return dateModel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date parseLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}