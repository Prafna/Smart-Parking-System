package parking.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import parking.dao.ParkingSessionDAO;
import parking.model.ParkingSession;

import java.time.LocalDateTime;
import java.time.Duration;

// Controls the Parking Exit GUI
public class ParkingExitController {

    @FXML
    private TextField sessionIdField;

    @FXML
    private Label exitTimeLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private Label feeLabel;

    private ParkingSessionDAO parkingSessionDAO;

    // Parking fee: Rs. 50 per hour
    private static final double HOURLY_RATE = 50.00;


    // Constructor
    public ParkingExitController() {

        parkingSessionDAO =
                new ParkingSessionDAO();
    }


    // Complete parking exit
    @FXML
    public void completeParkingExit() {

        try {

            String sessionText =
                    sessionIdField.getText().trim();

            if (sessionText.isEmpty()) {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Input Error",
                        "Please enter a Session ID."
                );

                return;
            }


            int sessionId =
                    Integer.parseInt(sessionText);


            // Find the parking session
            ParkingSession session =
                    parkingSessionDAO.getSessionById(
                            sessionId
                    );


            if (session == null) {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Parking session not found."
                );

                return;
            }


            // Check whether the session is already completed
            if ("Completed".equalsIgnoreCase(
                    session.getSessionStatus()
            )) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Already Completed",
                        "This parking session has already been completed."
                );

                return;
            }


            // Get exit time
            LocalDateTime exitTime =
                    LocalDateTime.now();


            // Calculate duration
            long durationMinutes =
                    Duration.between(
                            session.getEntryTime(),
                            exitTime
                    ).toMinutes();


            // Minimum one hour charge
            long hours =
                    (durationMinutes + 59) / 60;


            if (hours < 1) {
                hours = 1;
            }


            // Calculate parking fee
            double fee =
                    hours * HOURLY_RATE;


            // Update parking session
            boolean success =
                    parkingSessionDAO.completeSession(
                            sessionId,
                            exitTime,
                            (int) durationMinutes,
                            fee
                    );


            if (success) {

                exitTimeLabel.setText(
                        exitTime.toString()
                );

                durationLabel.setText(
                        durationMinutes + " minutes"
                );

                feeLabel.setText(
                        String.format(
                                "Rs. %.2f",
                                fee
                        )
                );


                showMessage(
                        Alert.AlertType.INFORMATION,
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
                        Alert.AlertType.ERROR,
                        "Error",
                        "Could not complete parking exit."
                );
            }


        } catch (NumberFormatException e) {

            showMessage(
                    Alert.AlertType.ERROR,
                    "Input Error",
                    "Session ID must be a whole number."
            );


        } catch (Exception e) {

            e.printStackTrace();

            showMessage(
                    Alert.AlertType.ERROR,
                    "Error",
                    "An unexpected error occurred."
            );
        }
    }


    // Display message
    private void showMessage(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}
