package de.dhbw.vigan.calendar.ui.dialogs;

import de.dhbw.vigan.calendar.ui.ApplicationUi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Settings window.
 */
public class SettingsDialog extends JDialog {
    public SettingsDialog(ApplicationUi applicationUi) {
        // This is needed so that the user is not able to select the main application until the settings windows is closed
        super(applicationUi, "Settings", true);

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
        JTextField calendarURL = new JTextField();
        settingsPanel.add(calendarURL);

        settingsPanel.add(new JLabel("Export file name:"));
        settingsPanel.add(new JTextField(applicationUi.options.ExportFileName));

        settingsPanel.add(new JLabel("Start date:"));
        settingsPanel.add(new JSpinner(new SpinnerDateModel()));

        settingsPanel.add(new JLabel("End date:"));
        settingsPanel.add(new JSpinner(new SpinnerDateModel()));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(settingsPanel, BorderLayout.CENTER);
    }

    private void setupButtonsPanel(ApplicationUi applicationUi) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this::submitAction);
        buttonPanel.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelAction);
        buttonPanel.add(cancelButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void submitAction(ActionEvent event) {
        setVisible(false);
    }

    private void cancelAction(ActionEvent event) {
        setVisible(false);
    }
}