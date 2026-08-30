package parking.service;

import parking.dao.UserDAO;
import parking.model.User;

import java.util.List;

// Service class for user-related operations
public class UserService 
{
    private final UserDAO userDAO;

    // Constructor
    public UserService() {
        userDAO = new UserDAO();
    }

    // Add a new user
    public boolean addUser(User user) {

        if (user == null) {
            System.out.println("User cannot be null.");
            return false;
        }

        if (user.getUsername() == null ||
                user.getUsername().trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return false;
        }

        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return false;
        }

        if (!isValidRole(user.getRole())) {
            System.out.println("Role must be Admin or Staff.");
            return false;
        }

        return userDAO.addUser(user);
    }

    // Get a user by ID
    public User getUserById(int userId) {

        if (userId <= 0) {
            System.out.println("Invalid user ID.");
            return null;
        }

        return userDAO.getUserById(userId);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Update an existing user
    public boolean updateUser(User user) {

        if (user == null) {
            System.out.println("User cannot be null.");
            return false;
        }

        if (user.getUserId() <= 0) {
            System.out.println("Invalid user ID.");
            return false;
        }

        if (user.getUsername() == null ||
                user.getUsername().trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return false;
        }

        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return false;
        }

        if (!isValidRole(user.getRole())) {
            System.out.println("Role must be Admin or Staff.");
            return false;
        }

        return userDAO.updateUser(user);
    }

    // Delete a user
    public boolean deleteUser(int userId) {

        if (userId <= 0) {
            System.out.println("Invalid user ID.");
            return false;
        }

        return userDAO.deleteUser(userId);
    }

    // Login / Authentication
    public User login(String username, String password) {

        if (username == null ||
                username.trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return null;
        }

        if (password == null ||
                password.trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return null;
        }

        return userDAO.authenticateUser(username, password);
    }

    // Validate user role
    private boolean isValidRole(String role) {

        return role != null &&
                (role.equalsIgnoreCase("Admin") ||
                 role.equalsIgnoreCase("Staff"));
    }
}
