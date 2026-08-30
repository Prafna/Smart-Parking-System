package parking.gui;

import parking.dao.ParkingSessionDAO;
import parking.model.ParkingSession;
import parking.service.PaymentService;

import javax.swing.*;
import java.awt.*;

// GUI for processing parking payments
public class PaymentGUI extends JFrame {

    private JTextField sessionIdField;
    private JLabel amountLabel;
    private JComboBox<String> paymentMethodBox;

    private ParkingSessionDAO parkingSessionDAO;
    private PaymentService paymentService;

    private double currentFee = 0.0;


    // Constructor
    public PaymentGUI() {

        parkingSessionDAO =
                new ParkingSessionDAO();

        paymentService =
                new PaymentService();

        // Window settings
        setTitle("Parking Payment");

        setSize(500, 400);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();
    }


    // Create the GUI
    private void createGUI() {

        JPanel mainPanel =
                new JPanel();

        mainPanel.setLayout(
                new GridLayout(
                        6,
                        2,
                        10,
                        15
                )
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );


        // Title
        JLabel titleLabel =
                new JLabel(
                        "PARKING PAYMENT"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );


        // Session ID
        JLabel sessionLabel =
                new JLabel(
                        "Session ID:"
                );

        sessionIdField =
                new JTextField();


        // Find session button
        JButton findButton =
                new JButton(
                        "Find Session"
                );


        // Amount
        JLabel amountTitle =
                new JLabel(
                        "Amount:"
                );

        amountLabel =
                new JLabel(
                        "Rs. 0.00"
                );


        // Payment method
        JLabel methodLabel =
                new JLabel(
                        "Payment Method:"
                );

        paymentMethodBox =
                new JComboBox<>(
                        new String[]{
                                "Cash",
                                "Card",
                                "Online"
                        }
                );


        // Status
        JLabel statusTitle =
                new JLabel(
                        "Status:"
                );

        JLabel statusLabel =
                new JLabel(
                        "Pending"
                );


        // Make payment button
        JButton payButton =
                new JButton(
                        "Make Payment"
                );


        // Add components
        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel(""));

        mainPanel.add(
                sessionLabel
        );

        mainPanel.add(
                sessionIdField
        );

        mainPanel.add(
                findButton
        );

        mainPanel.add(
                new JLabel("")
        );

        mainPanel.add(
                amountTitle
        );

        mainPanel.add(
                amountLabel
        );

        mainPanel.add(
                methodLabel
        );

        mainPanel.add(
                paymentMethodBox
        );

        mainPanel.add(
                statusTitle
        );

        mainPanel.add(
                statusLabel
        );


        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(
                payButton
        );


        add(
                mainPanel,
                BorderLayout.CENTER
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // Find session
        findButton.addActionListener(
                e -> findSession()
        );


        // Make payment
        payButton.addActionListener(
                e -> makePayment(statusLabel)
        );
    }


    // Find the completed parking session
    private void findSession() {

        try {

            String text =
                    sessionIdField
                            .getText()
                            .trim();


            if (text.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a Session ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            int sessionId =
                    Integer.parseInt(text);


            ParkingSession session =
                    parkingSessionDAO
                            .getSessionById(
                                    sessionId
                            );


            if (session == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Parking session not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // Payment should only happen
            // after parking exit
            if (!"Completed".equalsIgnoreCase(
                    session.getSessionStatus()
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "This parking session is still active.\n"
                                + "Please complete Parking Exit first.",
                        "Session Active",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // Get the fee from the completed session
            currentFee =
                    session.getFee();


            amountLabel.setText(
                    String.format(
                            "Rs. %.2f",
                            currentFee
                    )
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Completed parking session found.\n\n"
                            + "Session ID: "
                            + sessionId
                            + "\nParking Fee: Rs. "
                            + String.format(
                                    "%.2f",
                                    currentFee
                            ),
                    "Session Found",
                    JOptionPane.INFORMATION_MESSAGE
            );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Session ID must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Could not retrieve parking session.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // Process payment
    private void makePayment(
            JLabel statusLabel
    ) {

        try {

            String text =
                    sessionIdField
                            .getText()
                            .trim();


            if (text.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a Session ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            int sessionId =
                    Integer.parseInt(text);


            // Make sure a fee was loaded
            if (currentFee <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please click Find Session first.",
                        "Payment Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            String paymentMethod =
                    (String) paymentMethodBox
                            .getSelectedItem();


            // Use the existing PaymentService
            boolean success =
                    paymentService.makePayment(
                            sessionId,
                            currentFee,
                            paymentMethod
                    );


            if (success) {

                statusLabel.setText(
                        "Completed"
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Payment successful!\n\n"
                                + "Session ID: "
                                + sessionId
                                + "\nAmount: Rs. "
                                + String.format(
                                        "%.2f",
                                        currentFee
                                )
                                + "\nPayment Method: "
                                + paymentMethod
                                + "\nStatus: Completed",
                        "Payment Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );


                sessionIdField.setEnabled(
                        false
                );

                paymentMethodBox.setEnabled(
                        false
                );


            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment could not be completed.",
                        "Payment Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Session ID must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while processing payment.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}