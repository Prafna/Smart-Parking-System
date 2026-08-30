package parking.gui;

import parking.dao.ParkingSessionDAO;
import parking.dao.ParkingSlotDAO;
import parking.model.ParkingSession;
import parking.model.ParkingSlot;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class ParkingEntryGUI extends JFrame {

    private JTextField vehicleIdField;
    private JComboBox<String> slotComboBox;

    private ParkingSlotDAO parkingSlotDAO;
    private ParkingSessionDAO parkingSessionDAO;

    public ParkingEntryGUI() {

        parkingSlotDAO = new ParkingSlotDAO();
        parkingSessionDAO = new ParkingSessionDAO();

        setTitle("Parking Entry");
        setSize(500, 400);
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

            slotComboBox.addItem(
                    slot.getSlotId()
                            + " - "
                            + slot.getSlotNumber()
                            + " - "
                            + slot.getSlotType()
            );
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

            // Get selected slot information
            String selectedSlot =
                    slotComboBox
                            .getSelectedItem()
                            .toString();

            // Extract Slot ID
            int slotId =
                    Integer.parseInt(
                            selectedSlot
                                    .split(" - ")[0]
                    );

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
                parkingSlotDAO
                        .updateSlotStatus(
                                slotId,
                                "Occupied"
                        );

                JOptionPane.showMessageDialog(
                        this,
                        "Parking session started successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                vehicleIdField.setText("");

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
                    "An unexpected error occurred.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
