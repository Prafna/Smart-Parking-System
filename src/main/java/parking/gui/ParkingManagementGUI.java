package parking.gui;

import javax.swing.*;
import java.awt.*;

public class ParkingManagementGUI extends JFrame {

    public ParkingManagementGUI() {

        setTitle("Parking Management System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel(
                "PARKING MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        panel.add(title, BorderLayout.NORTH);

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
                new GridLayout(6, 1, 10, 10)
        );

        // =========================
        // BUTTONS
        // =========================

        JButton vehicleButton =
                new JButton("Vehicle Registration");

        JButton slotButton =
                new JButton("Parking Slots");

        JButton entryButton =
                new JButton("Parking Entry");

        JButton exitButton =
                new JButton("Parking Exit");

        JButton sessionButton =
                new JButton("Parking Sessions");

        JButton paymentButton =
                new JButton("Payment");

        // =========================
        // VEHICLE REGISTRATION
        // =========================

        vehicleButton.addActionListener(e -> {

            VehicleManagementGUI vehicleGUI =
                    new VehicleManagementGUI();

            vehicleGUI.setVisible(true);
        });

        // =========================
        // PARKING SLOTS
        // =========================

        slotButton.addActionListener(e -> {

            ParkingSlotGUI slotGUI =
                    new ParkingSlotGUI();

            slotGUI.setVisible(true);
        });

        // =========================
        // PARKING ENTRY
        // =========================

        entryButton.addActionListener(e -> {

            ParkingEntryGUI entryGUI =
                    new ParkingEntryGUI();

            entryGUI.setVisible(true);
        });

        // =========================
        // PARKING EXIT
        // =========================

        exitButton.addActionListener(e -> {

            ParkingExitGUI exitGUI =
                    new ParkingExitGUI();

            exitGUI.setVisible(true);
        });

        // =========================
        // PARKING SESSIONS
        // =========================

        sessionButton.addActionListener(e -> {

            ParkingSessionGUI sessionGUI =
                    new ParkingSessionGUI();

            sessionGUI.setVisible(true);
        });

        // =========================
        // PAYMENT
        // =========================

        paymentButton.addActionListener(e -> {

            PaymentGUI paymentGUI =
                    new PaymentGUI();

            paymentGUI.setVisible(true);
        });

        // =========================
        // ADD BUTTONS
        // =========================

        buttonPanel.add(vehicleButton);
        buttonPanel.add(slotButton);
        buttonPanel.add(entryButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(sessionButton);
        buttonPanel.add(paymentButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // =========================
        // CLOSE BUTTON
        // =========================

        JButton closeButton =
                new JButton("Close");

        closeButton.addActionListener(e ->
                dispose()
        );

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Add main panel
        add(panel);
    }

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ParkingManagementGUI gui =
                    new ParkingManagementGUI();

            gui.setVisible(true);
        });
    }
}
