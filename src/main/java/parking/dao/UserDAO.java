package parking.dao;

import parking.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO 
{
    // CREATE - Add a new user
    public boolean addUser(User user) {

        String sql = "INSERT INTO Users (Username, Password, Role_) VALUES (?, ?, ?)";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error adding user to the database.");
            e.printStackTrace();

            return false;
        }
    }

    // READ - Get user by ID
    public User getUserById(int userId) {

        String sql =
                "SELECT UserID, Username, Password, Role_ " +
                "FROM Users WHERE UserID = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role_")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println("Error retrieving user.");
            e.printStackTrace();
        }

        return null;
    }

    // READ - Get all users
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql =
                "SELECT UserID, Username, Password, Role_ " +
                "FROM Users";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                User user = new User(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Role_")
                );

                users.add(user);
            }

        } catch (SQLException e) {

            System.out.println("Error retrieving users.");
            e.printStackTrace();
        }

        return users;
    }

    // UPDATE - Update an existing user
    public boolean updateUser(User user) {

        String sql =
                "UPDATE Users " +
                "SET Username = ?, Password = ?, Role_ = ? " +
                "WHERE UserID = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getUserId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error updating user.");
            e.printStackTrace();

            return false;
        }
    }

    // DELETE - Delete a user
    public boolean deleteUser(int userId) {

        String sql =
                "DELETE FROM Users WHERE UserID = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting user.");
            e.printStackTrace();

            return false;
        }
    }

    // LOGIN - Authenticate user
    public User authenticateUser(String username, String password) {

        String sql =
                "SELECT UserID, Username, Password, Role_ " +
                "FROM Users " +
                "WHERE Username = ? AND Password = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role_")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println("Error authenticating user.");
            e.printStackTrace();
        }

        return null;
    }
}
