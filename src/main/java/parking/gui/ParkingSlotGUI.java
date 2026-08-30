package parking.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import parking.dao.ParkingSlotDAO;
import parking.model.ParkingSlot;

// GUI for displaying parking slots
public class ParkingSlotGUI extends JFrame {

    private ParkingSlotDAO parkingSlotDAO;

    public ParkingSlotGUI() {

        // =========================
        // WINDOW SETTINGS
        // =========================

        setTitle("Parking Slots");

        setSize(750, 550);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);


        // =========================
        // MAIN PANEL
        // =========================

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel(
                        "PARKING SLOTS",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );


        // =========================
        // TEXT AREA
        // =========================

        JTextArea slotArea =
                new JTextArea();

        slotArea.setEditable(false);

        slotArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        slotArea.setLineWrap(false);


        // =========================
        // GET SLOTS
        // =========================

        try {

            parkingSlotDAO =
                    new ParkingSlotDAO();

            List<ParkingSlot> slots =
                    parkingSlotDAO.getAllSlots();


            if (slots == null || slots.isEmpty()) {

                slotArea.setText(
                        "No parking slots found."
                );

            } else {

                for (
                        ParkingSlot slot : slots
                ) {

                    slotArea.append(
                            "Slot ID: "
                            + slot.getSlotId()
                            + "   |   Slot Number: "
                            + slot.getSlotNumber()
                            + "   |   Type: "
                            + slot.getSlotType()
                            + "   |   Status: "
                            + slot.getStatus()
                            + "\n\n"
                    );
                }
            }


        } catch (Exception e) {

            e.printStackTrace();

            slotArea.setText(
                    "Unable to load parking slots.\n\n"
                    + "Error: "
                    + e.getMessage()
            );
        }


        // =========================
        // SCROLL PANE
        // =========================

        JScrollPane scrollPane =
                new JScrollPane(
                        slotArea
                );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );


        // =========================
        // ADD COMPONENTS
        // =========================

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        add(panel);
    }
}
