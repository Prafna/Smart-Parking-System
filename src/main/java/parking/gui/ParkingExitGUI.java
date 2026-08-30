package parking.gui;

import parking.service.ParkingService;
import parking.model.ParkingSession;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingExitGUI extends JFrame {

    private JTextField sessionIdField;

    private JLabel exitTimeLabel;
    private JLabel durationLabel;
    private JLabel feeLabel;

    private ParkingService ParkingService;

    // Parking fee: Rs. 50 per hour
    private static final double HOURLY_RATE = 50.00;


    public ParkingExitGUI() {

        ParkingService =
        new ParkingService();

        setTitle("Parking Exit");

        setSize(600, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();
    }


    private void createGUI() {

        JPanel mainPanel =
                new JPanel();

        mainPanel.setLayout(
                new GridLayout(
                        7,
                        2,
                        10,
                        15
                )
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );


       
        // TITLE
        

        JLabel title =
                new JLabel(
                        "PARKING EXIT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );


        
        // SESSION ID
        

        JLabel sessionIdLabel =
                new JLabel(
                        "Session ID:"
                );

        sessionIdField =
                new JTextField();


        // =========================
        // EXIT TIME
        // =========================

        JLabel exitTimeText =
                new JLabel(
                        "Exit Time:"
                );

        exitTimeLabel =
                new JLabel(
                        "-"
                );


        
        // DURATION
        
        JLabel durationText =
                new JLabel(
                        "Duration:"
                );

        durationLabel =
                new JLabel(
                        "-"
                );


       
        // PARKING FEE
        
        JLabel feeText =
                new JLabel(
                        "Parking Fee:"
                );

        feeLabel =
                new JLabel(
                        "Rs. 0.00"
                );


        
        // BUTTON
       
        JButton exitButton =
                new JButton(
                        "Complete Parking Exit"
                );

        exitButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        exitButton.addActionListener(
                e -> completeParkingExit()
        );


        
        // ADD COMPONENTS
        

        mainPanel.add(
                title
        );

        mainPanel.add(
                new JLabel("")
        );


        mainPanel.add(
                sessionIdLabel
        );

        mainPanel.add(
                sessionIdField
        );


        mainPanel.add(
                exitTimeText
        );

        mainPanel.add(
                exitTimeLabel
        );


        mainPanel.add(
                durationText
        );

        mainPanel.add(
                durationLabel
        );


        mainPanel.add(
                feeText
        );

        mainPanel.add(
                feeLabel
        );


        mainPanel.add(
                new JLabel("")
        );

        mainPanel.add(
                exitButton
        );


        add(mainPanel);
    }


    
    // COMPLETE PARKING EXIT
    

    private void completeParkingExit() {

        try {

            String sessionText =
                    sessionIdField
                            .getText()
                            .trim();


            // Check empty input

            if (sessionText.isEmpty()) {

                showMessage(
                        JOptionPane.ERROR_MESSAGE,
                        "Input Error",
                        "Please enter a Session ID."
                );

                return;
            }


            // Convert Session ID

            int sessionId =
                    Integer.parseInt(
                            sessionText
                    );


            // Find session

            ParkingSession session =
           ParkingService
                .getSessionById(
                        sessionId
                );


            if (session == null) {

                showMessage(
                        JOptionPane.ERROR_MESSAGE,
                        "Error",
                        "Parking session not found."
                );

                return;
            }


            // Check if already completed

            if (
                    "Completed"
                            .equalsIgnoreCase(
                                    session.getSessionStatus()
                            )
            ) {

                showMessage(
                        JOptionPane.WARNING_MESSAGE,
                        "Already Completed",
                        "This parking session has already been completed."
                );

                return;
            }


            
            // EXIT TIME
            
            LocalDateTime exitTime =
                    LocalDateTime.now();


            
            // CALCULATE DURATION
            

            long durationMinutes =
                    Duration.between(
                            session.getEntryTime(),
                            exitTime
                    ).toMinutes();


           
            // CALCULATE HOURS
            

            long hours =
                    (durationMinutes + 59) / 60;


            if (hours < 1) {
                hours = 1;
            }


            
            // CALCULATE FEE
            

            double fee =
                    hours * HOURLY_RATE;


            
            // UPDATE DATABASE
            

            boolean success =
           ParkingService
                .completeParkingSession(
                        sessionId
                );


            if (success) {

                exitTimeLabel.setText(
                        exitTime.toString()
                );

                durationLabel.setText(
                        durationMinutes
                                + " minutes"
                );

                feeLabel.setText(
                        String.format(
                                "Rs. %.2f",
                                fee
                        )
                );


                showMessage(
                        JOptionPane.INFORMATION_MESSAGE,
                        "Parking Exit Successful",
                        "Parking session completed successfully.\n\n"
                                + "Session ID: "
                                + sessionId
                                + "\nDuration: "
                                + durationMinutes
                                + " minutes"
                                + "\nParking Fee: Rs. "
                                + String.format(
                                        "%.2f",
                                        fee
                                )
                );


            } else {

                showMessage(
                        JOptionPane.ERROR_MESSAGE,
                        "Error",
                        "Could not complete parking exit."
                );
            }


        } catch (NumberFormatException e) {

            showMessage(
                    JOptionPane.ERROR_MESSAGE,
                    "Input Error",
                    "Session ID must be a whole number."
            );


        } catch (Exception e) {

            e.printStackTrace();

            showMessage(
                    JOptionPane.ERROR_MESSAGE,
                    "Error",
                    "An unexpected error occurred:\n"
                            + e.getMessage()
            );
        }
    }


    
    // MESSAGE BOX
    

    private void showMessage(
            int messageType,
            String title,
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                messageType
        );
    }
}
