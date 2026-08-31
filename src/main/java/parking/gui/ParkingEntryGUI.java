package parking.gui;

import parking.dao.ParkingSessionDAO;
import parking.dao.ParkingSlotDAO;
import parking.model.ParkingSession;
import parking.model.ParkingSlot;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingEntryGUI extends JFrame {

    private JTextField vehicleIdField;
    private JComboBox<ParkingSlot> slotComboBox;

    private ParkingSlotDAO parkingSlotDAO;
    private ParkingSessionDAO parkingSessionDAO;

    public ParkingEntryGUI() {

        parkingSlotDAO = new ParkingSlotDAO();
        parkingSessionDAO = new ParkingSessionDAO();

        setTitle("Parking Entry");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
        loadAvailableSlots();
    }

    private void createGUI() {

        JPanel panel = new JPanel(
                new GridLayout(6, 1, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 40, 30, 40
                )
        );

        JLabel title =
                new JLabel(
                        "START PARKING SESSION",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        JLabel vehicleLabel =
                new JLabel("Vehicle ID:");

        vehicleIdField =
                new JTextField();

        JLabel slotLabel =
                new JLabel("Select Available Slot:");

        slotComboBox =
                new JComboBox<>();

        JButton startButton =
                new JButton("Start Parking");

        startButton.addActionListener(
                e -> startParkingSession()
        );

        panel.add(title);
        panel.add(vehicleLabel);
        panel.add(vehicleIdField);
        panel.add(slotLabel);
        panel.add(slotComboBox);
        panel.add(startButton);

        add(panel);
    }

    private void loadAvailableSlots() {

        List<ParkingSlot> slots =
                parkingSlotDAO.getAvailableSlots();

        slotComboBox.removeAllItems();

        for (ParkingSlot slot : slots) {

            // Add the complete ParkingSlot object
            slotComboBox.addItem(slot);
        }

        if (slots.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No parking slots are currently available.",
                    "Parking Slots",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void startParkingSession() {

        try {

            // Get Vehicle ID
            int vehicleId =
                    Integer.parseInt(
                            vehicleIdField
                                    .getText()
                                    .trim()
                    );

            // Make sure a slot was selected
            if (slotComboBox.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an available parking slot.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Get the selected ParkingSlot object
            ParkingSlot selectedSlot =
                    (ParkingSlot)
                            slotComboBox.getSelectedItem();

            // Get Slot ID
            int slotId =
                    selectedSlot.getSlotId();

            // Create parking session
            ParkingSession session =
                    new ParkingSession(
                            0,
                            vehicleId,
                            slotId,
                            LocalDateTime.now()
                    );

            // Save session
            boolean success =
                    parkingSessionDAO
                            .createSession(session);

            if (success) {

                // Change slot status to Occupied
                boolean slotUpdated =
                        parkingSlotDAO.updateSlotStatus(
                                slotId,
                                "Occupied"
                        );

                if (!slotUpdated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Parking session was created, " +
                            "but the slot status could not be updated.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                /*
                 * Retrieve the newly created active session.
                 * The database has generated the Session ID.
                 */
                ParkingSession createdSession =
                        parkingSessionDAO
                                .getActiveSessionByVehicle(
                                        vehicleId
                                );

                if (createdSession != null) {

                    // Format entry time for the user
                    DateTimeFormatter formatter =
                            DateTimeFormatter.ofPattern(
                                    "dd MMM yyyy, hh:mm a"
                            );

                    String formattedEntryTime =
                            createdSession
                                    .getEntryTime()
                                    .format(formatter);

                    // Display complete parking information
                    String message =
                            "PARKING SESSION STARTED SUCCESSFULLY!\n\n" +

                            "Session ID   : "
                            + createdSession.getSessionId()
                            + "\n" +

                            "Vehicle ID   : "
                            + createdSession.getVehicleId()
                            + "\n" +

                            "Slot ID      : "
                            + createdSession.getSlotId()
                            + "\n" +

                            "Slot Number  : "
                            + selectedSlot.getSlotNumber()
                            + "\n" +

                            "Slot Type    : "
                            + selectedSlot.getSlotType()
                            + "\n" +

                            "Entry Time   : "
                            + formattedEntryTime
                            + "\n" +

                            "Status       : "
                            + createdSession.getSessionStatus()
                            + "\n\n" +

                            "Please keep your Session ID " +
                            "for the payment process.";

                    JOptionPane.showMessageDialog(
                            this,
                            message,
                            "Parking Session Details",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Parking session started, " +
                            "but the session details could not be retrieved.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

                // Clear Vehicle ID field
                vehicleIdField.setText("");

                // Reload available slots
                loadAvailableSlots();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Could not start parking session.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Vehicle ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "An unexpected error occurred:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}