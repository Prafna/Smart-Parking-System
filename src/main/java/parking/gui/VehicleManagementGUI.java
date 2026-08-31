package parking.gui;

import parking.dao.VehicleDAO;
import parking.model.Car;
import parking.model.Motorbike;
import parking.model.Van;
import parking.model.Vehicle;

import javax.swing.*;
import java.awt.*;

public class VehicleManagementGUI extends JFrame {

    private JTextField vehicleNumberField;
    private JTextField ownerNameField;
    private JTextField contactNumberField;
    private JComboBox<String> vehicleTypeComboBox;

    private VehicleDAO vehicleDAO;


    public VehicleManagementGUI() {

        vehicleDAO = new VehicleDAO();

        setTitle("Vehicle Registration");

        setSize(600, 450);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();
    }


    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );


        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel(
                        "VEHICLE REGISTRATION",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );


        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                15
                        )
                );


        // Vehicle Number

        JLabel vehicleNumberLabel =
                new JLabel("Vehicle Number:");

        vehicleNumberField =
                new JTextField();


        // Owner Name

        JLabel ownerNameLabel =
                new JLabel("Owner Name:");

        ownerNameField =
                new JTextField();


        // Contact Number

        JLabel contactNumberLabel =
                new JLabel("Contact Number:");

        contactNumberField =
                new JTextField();


        // Vehicle Type

        JLabel vehicleTypeLabel =
                new JLabel("Vehicle Type:");

        vehicleTypeComboBox =
                new JComboBox<>(
                        new String[]{
                                "Car",
                                "Motorbike",
                                "Van"
                        }
                );


        formPanel.add(
                vehicleNumberLabel
        );

        formPanel.add(
                vehicleNumberField
        );

        formPanel.add(
                ownerNameLabel
        );

        formPanel.add(
                ownerNameField
        );

        formPanel.add(
                contactNumberLabel
        );

        formPanel.add(
                contactNumberField
        );

        formPanel.add(
                vehicleTypeLabel
        );

        formPanel.add(
                vehicleTypeComboBox
        );


        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );


        // =========================
        // REGISTER BUTTON
        // =========================

        JButton registerButton =
                new JButton(
                        "Register Vehicle"
                );

        registerButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );


        registerButton.addActionListener(
                e -> registerVehicle()
        );


        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(
                registerButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        add(mainPanel);
    }


    // =========================
    // REGISTER VEHICLE
    // =========================

    private void registerVehicle() {

        String vehicleNumber =
                vehicleNumberField
                        .getText()
                        .trim();

        String ownerName =
                ownerNameField
                        .getText()
                        .trim();

        String contactNumber =
                contactNumberField
                        .getText()
                        .trim();

        String vehicleType =
                (String)
                        vehicleTypeComboBox
                                .getSelectedItem();


        // =========================
        // VALIDATION
        // =========================

        if (
                vehicleNumber.isEmpty()
                || ownerName.isEmpty()
                || contactNumber.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        try {

            // Create the correct Vehicle object

            Vehicle vehicle;


            if (
                    vehicleType.equalsIgnoreCase(
                            "Car"
                    )
            ) {

                vehicle =
                        new Car(
                                0,
                                vehicleNumber,
                                ownerName,
                                contactNumber
                        );

            } else if (
                    vehicleType.equalsIgnoreCase(
                            "Motorbike"
                    )
            ) {

                vehicle =
                        new Motorbike(
                                0,
                                vehicleNumber,
                                ownerName,
                                contactNumber
                        );

            } else {

                vehicle =
                        new Van(
                                0,
                                vehicleNumber,
                                ownerName,
                                contactNumber
                        );
            }


            // Save vehicle to database

            boolean success =
                    vehicleDAO.addVehicle(
                            vehicle
                    );


                if (success) {

        String message =
            "VEHICLE REGISTERED SUCCESSFULLY!\n\n" +
            "Vehicle ID      : " + vehicle.getVehicleId() + "\n" +
            "Vehicle Number  : " + vehicle.getVehicleNumber() + "\n" +
            "Owner Name      : " + vehicle.getOwnerName() + "\n" +
            "Contact Number  : " + vehicle.getContactNumber() + "\n" +
            "Vehicle Type    : " + vehicle.getVehicleType() + "\n\n" +
            "Please remember your Vehicle ID for parking.";

         JOptionPane.showMessageDialog(
            this,
            message,
            "Vehicle Registration Details",
            JOptionPane.INFORMATION_MESSAGE
       );

    // Clear fields

                // Clear fields

                vehicleNumberField.setText("");

                ownerNameField.setText("");

                contactNumberField.setText("");

                vehicleTypeComboBox
                        .setSelectedIndex(0);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Vehicle registration failed.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error registering vehicle:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}