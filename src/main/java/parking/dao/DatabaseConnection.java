package parking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection 
{

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=ParkingManagementSystem;"
            + "encrypt=true;"
            + "trustServerCertificate=true";

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "parking123";

    public static Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    public static void main(String[] args) 
    {

        try (Connection connection = getConnection()) {

            System.out.println("Database connected successfully!");

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}