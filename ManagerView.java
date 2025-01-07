package View;

import Model.Customer;
import Model.Parcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerView {
    private JFrame frame;
    private JButton loadButton;
    private JButton displayButton;
    private JButton processButton;
    private JButton saveButton;
    private JButton logButton;
    private JButton transitionButton;
    private JButton findButton;
    private JTextArea outputArea;
    private JLabel statusBar;

    public ManagerView() {
        initializeUI();
    }

    private void initializeUI() {
        // Create the main frame
        frame = new JFrame("Parcel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        // Create the main panel with layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create button panel
        JPanel buttonPanel = createButtonPanel();

        // Create a text area for logs and output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Status bar
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createEtchedBorder());

        // Add components to the main panel
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        // Set up the frame
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10)); // Two rows, four columns
        loadButton = new JButton("Load Data");
        displayButton = new JButton("Display Data");
        processButton = new JButton("Process Customer");
        saveButton = new JButton("Add Customer");
        transitionButton = new JButton("Add Parcel");
        findButton = new JButton("Find Parcel");
        logButton = new JButton("Generate Report");

        buttonPanel.add(loadButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(processButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(logButton);
        buttonPanel.add(transitionButton);
        buttonPanel.add(findButton);

        return buttonPanel;
    }

    // Method to append output text
    public void appendOutput(String text) {
        outputArea.append(text + "\n");
    }

    // Method to update status text
    public void updateStatus(String text) {
        statusBar.setText(text);
    }

    // Add customer input dialog
    public Customer getCustomerInput() {
        String firstName = JOptionPane.showInputDialog(frame, "Enter Customer First Name:");
        String lastName = JOptionPane.showInputDialog(frame, "Enter Customer Last Name:");
        String parcelID = JOptionPane.showInputDialog(frame, "Enter Parcel ID:");

        if (firstName != null && lastName != null && parcelID != null &&
                !firstName.trim().isEmpty() && !lastName.trim().isEmpty() && !parcelID.trim().isEmpty()) {
            return new Customer(firstName.trim(), lastName.trim(), parcelID.trim());
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Get parcel input dialog
    public Parcel getParcelInput() {
        try {
            String parcelID = JOptionPane.showInputDialog(frame, "Enter Parcel ID:");
            String lengthStr = JOptionPane.showInputDialog(frame, "Enter Parcel Length:");
            String widthStr = JOptionPane.showInputDialog(frame, "Enter Parcel Width:");
            String heightStr = JOptionPane.showInputDialog(frame, "Enter Parcel Height:");
            String weightStr = JOptionPane.showInputDialog(frame, "Enter Parcel Weight:");
            String daysInDepotStr = JOptionPane.showInputDialog(frame, "Enter Days in Depot:");
            String status = JOptionPane.showInputDialog(frame, "Enter Parcel Status:");

            // Check if any input is null or empty
            if (parcelID != null && !parcelID.trim().isEmpty() &&
                    lengthStr != null && !lengthStr.trim().isEmpty() &&
                    widthStr != null && !widthStr.trim().isEmpty() &&
                    heightStr != null && !heightStr.trim().isEmpty() &&
                    weightStr != null && !weightStr.trim().isEmpty() &&
                    daysInDepotStr != null && !daysInDepotStr.trim().isEmpty() &&
                    status != null && !status.trim().isEmpty()) {

                // Parse numeric fields
                double length = Double.parseDouble(lengthStr.trim());
                double width = Double.parseDouble(widthStr.trim());
                double height = Double.parseDouble(heightStr.trim());
                double weight = Double.parseDouble(weightStr.trim());
                int daysInDepot = Integer.parseInt(daysInDepotStr.trim());

                // Create and return a new Parcel object
                return new Parcel(parcelID.trim(), length, width, height, weight, daysInDepot, status.trim());
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid input, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid numeric input, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Find parcel input dialog in ManagerView
    public String getParcelIdInput() {
        return JOptionPane.showInputDialog(frame, "Enter Parcel ID to find:");
    }


    // Add listener methods for buttons
    public void addLoadButtonListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }

    public void addDisplayButtonListener(ActionListener listener) {
        displayButton.addActionListener(listener);
    }

    public void addProcessButtonListener(ActionListener listener) {
        processButton.addActionListener(listener);
    }

    public void addAddCustomerButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addGenerateReportButtonListener(ActionListener listener) {
        logButton.addActionListener(listener);
    }

    public void addAddParcelButtonListener(ActionListener listener) {
        transitionButton.addActionListener(listener);
    }

    public void addFindParcelButtonListener(ActionListener listener) {
        findButton.addActionListener(listener);
    }

    // Getters for buttons (if needed in the controller)
    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getDisplayButton() {
        return displayButton;
    }

    public JButton getProcessButton() {
        return processButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLogButton() {
        return logButton;
    }

    public JButton getTransitionButton() {
        return transitionButton;
    }

    public JButton getClearButton() {
        return findButton;
    }
}
