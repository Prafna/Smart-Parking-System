package parking.controller;

import parking.service.PaymentService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

// Controls the Payment GUI
public class PaymentController {

    // Connects with Session ID field
    @FXML
    private TextField sessionIdField;

    // Connects with Amount field
    @FXML
    private TextField amountField;

    // Connects with Payment Method field
    @FXML
    private TextField paymentMethodField;


    // Create PaymentService object
    private PaymentService paymentService =
            new PaymentService();


    // Called when Make Payment button is clicked
    @FXML
    public void makePayment() {

        try {

            // Convert Session ID into integer
            int sessionId =
                    Integer.parseInt(
                            sessionIdField.getText()
                    );

            // Convert amount into double
            double amount =
                    Double.parseDouble(
                            amountField.getText()
                    );

            // Get payment method
            String paymentMethod =
                    paymentMethodField.getText();


            // Call service to process payment
            boolean success =
                    paymentService.makePayment(

                            sessionId,

                            amount,

                            paymentMethod
                    );


            // Check if payment was successful
            if (success) {

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Payment completed successfully."
                );

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Payment failed."
                );
            }

        } catch (NumberFormatException e) {

            // User entered invalid numbers
            showMessage(
                    Alert.AlertType.ERROR,
                    "Input Error",
                    "Please enter valid Session ID and Amount."
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


    // Reusable method for showing messages
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