package parking.model;

public class User 
{
    private int userId;
    private String username;
    private String password;
    private String role;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Get User ID
    public int getUserId() {
        return userId;
    }

    // Set User ID
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Get username
    public String getUsername() {
        return username;
    }

    // Set username
    public void setUsername(String username) {
        this.username = username;
    }

    // Get password
    public String getPassword() {
        return password;
    }

    // Set password
    public void setPassword(String password) {
        this.password = password;
    }

    // Get role
    public String getRole() {
        return role;
    }

    // Set role
    public void setRole(String role) {
        this.role = role;
    }

    // Get name
    public String getName() {
        return username;
    }

    // Display user information
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}