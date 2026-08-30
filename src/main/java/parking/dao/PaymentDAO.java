package parking.dao;

import parking.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

// This class handles payment database operations
public class PaymentDAO {

    // Save a new payment into the database
    public boolean createPayment(Payment payment) {

        String sql =
                "INSERT INTO Payments " +
                "(SessionID, Amount, PaymentMethod, PaymentDate, PaymentStatus) " +
                "VALUES (?, ?, ?, ?, ?)";

        // Connect to the database
        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            // Set values into the SQL query
            statement.setInt(
                    1,
                    payment.getSessionId()
            );

            statement.setDouble(
                    2,
                    payment.getAmount()
            );

            statement.setString(
                    3,
                    payment.getPaymentMethod()
            );

            // Convert LocalDateTime into SQL Timestamp
            statement.setTimestamp(
                    4,
                    Timestamp.valueOf(
                            payment.getPaymentDate()
                    )
            );

            statement.setString(
                    5,
                    payment.getPaymentStatus()
            );

            // Execute the INSERT query
            int rowsInserted =
                    statement.executeUpdate();

            // Return true if payment was inserted
            return rowsInserted > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error creating payment."
            );

            e.printStackTrace();

            return false;
        }
    }


    // Find payment using the parking session ID
    public Payment getPaymentBySessionId(
            int sessionId
    ) {

        String sql =
                "SELECT * FROM Payments " +
                "WHERE SessionID = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            // Set session ID
            statement.setInt(
                    1,
                    sessionId
            );

            // Execute SELECT query
            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                // Check if a payment exists
                if (resultSet.next()) {

                    return new Payment(

                            resultSet.getInt(
                                    "PaymentID"
                            ),

                            resultSet.getInt(
                                    "SessionID"
                            ),

                            resultSet.getDouble(
                                    "Amount"
                            ),

                            resultSet.getString(
                                    "PaymentMethod"
                            ),

                            resultSet
                                    .getTimestamp(
                                            "PaymentDate"
                                    )
                                    .toLocalDateTime(),

                            resultSet.getString(
                                    "PaymentStatus"
                            )
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving payment."
            );

            e.printStackTrace();
        }

        // Return null if no payment was found
        return null;
    }
}