package de.dhbw.vigan.calendar.ui.dialogs;

import de.dhbw.vigan.calendar.ui.ApplicationUi;
import de.dhbw.vigan.calendar.core.services.settings.CalendarSettings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Settings window.
 */
public class SettingsDialog extends JDialog {
    private final CalendarSettings settings;

    private JTextField calendarUrl;

    public SettingsDialog(ApplicationUi applicationUi) {
        // This is needed so that the user is not able to select the main application until the settings windows is closed
        super(applicationUi, "Settings", true);

        settings = applicationUi.settings;

        // setSize(512, 256);
        setResizable(false);
        setLocationRelativeTo(applicationUi);
        setIconImage(applicationUi.getIconImage());

        setupSettingsPanel(applicationUi);
        setupButtonsPanel(applicationUi);

        pack();
    }

    private void setupSettingsPanel(ApplicationUi applicationUi) {
        JPanel settingsPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        settingsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        settingsPanel.add(new JLabel("Calendar URL:"));
        calendarUrl = new JTextField();
        settingsPanel.add(calendarUrl);

        settingsPanel.add(new JLabel("Export file name:"));
        JTextField exportFile = new JTextField(applicationUi.options.exportFileName);
        settingsPanel.add(exportFile);

        settingsPanel.add(new JLabel("Start date:"));
        JSpinner startDate = new JSpinner(new SpinnerDateModel());
        settingsPanel.add(startDate);

        settingsPanel.add(new JLabel("End date:"));
        JSpinner endDate = new JSpinner(new SpinnerDateModel());
        settingsPanel.add(endDate);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(settingsPanel, BorderLayout.CENTER);
    }

    private void setupButtonsPanel(ApplicationUi applicationUi) {
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
        settings.calendarUrl = calendarUrl.getText();
    }
}