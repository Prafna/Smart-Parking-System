package parking.gui;

import parking.model.User;
import parking.service.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame 
{

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton exitButton;

    private final UserService userService;

    // COLOUR THEME

    private final Color BACKGROUND =
            new Color(238, 242, 247);

    private final Color PRIMARY =
            new Color(25, 85, 140);

    private final Color PRIMARY_DARK =
            new Color(18, 63, 105);

    private final Color SECONDARY =
            new Color(90, 100, 115);

    private final Color WHITE =
            Color.WHITE;

    private final Color DARK =
            new Color(35, 45, 55);

    private final Color BORDER =
            new Color(200, 208, 218);

    // DASHBOARD WINDOW
    private static class DashboardGUI extends JFrame {

        DashboardGUI(User user) {
            super("Dashboard");
            setSize(900, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            JLabel welcomeLabel = new JLabel(
                    "Welcome, " + (user != null ? user.getUsername() : "User")
            );
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            add(welcomeLabel, BorderLayout.CENTER);
        }
    }

    // CONSTRUCTOR

    public LoginGUI() {

        userService = new UserService();

        setTitle(
                "Smart Parking System - Login"
        );

        setSize(
                650,
                600
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();
    }

    // CREATE GUI

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        // HEADER

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setBackground(
                PRIMARY
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        20,
                        30,
                        20
                )
        );


        // MAIN TITLE

        JLabel titleLabel =
                new JLabel(
                        "SMART PARKING SYSTEM"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                WHITE
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // SUBTITLE

        JLabel subtitleLabel =
                new JLabel(
                        "Parking Management System"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        220,
                        230,
                        240
                )
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        headerPanel.add(
                titleLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(8)
        );

        headerPanel.add(
                subtitleLabel
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // CENTER AREA

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        // LOGIN CARD

        JPanel loginPanel =
                new JPanel();

        loginPanel.setLayout(
                new BoxLayout(
                        loginPanel,
                        BoxLayout.Y_AXIS
                )
        );

        loginPanel.setBackground(
                WHITE
        );

        loginPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                30,
                                45,
                                30,
                                45
                        )
                )
        );

        // LOGIN TITLE

        JLabel loginTitle =
                new JLabel(
                        "Welcome back!"
                );

        loginTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        loginTitle.setForeground(
                DARK
        );

        loginTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        loginPanel.add(
                loginTitle
        );


        loginPanel.add(
                Box.createVerticalStrut(25)
        );

        // USERNAME LABEL

        JLabel usernameLabel =
                new JLabel(
                        "USERNAME"
                );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        usernameLabel.setForeground(
                DARK
        );

        usernameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        loginPanel.add(
                usernameLabel
        );


        loginPanel.add(
                Box.createVerticalStrut(8)
        );

        // USERNAME FIELD

        usernameField =
                new JTextField();

        styleTextField(
                usernameField
        );

        usernameField.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(
                usernameField
        );


        loginPanel.add(
                Box.createVerticalStrut(18)
        );

        // PASSWORD LABEL

        JLabel passwordLabel =
                new JLabel(
                        "PASSWORD"
                );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        passwordLabel.setForeground(
                DARK
        );

        passwordLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        loginPanel.add(
                passwordLabel
        );


        loginPanel.add(
                Box.createVerticalStrut(8)
        );

        // PASSWORD FIELD

        passwordField =
                new JPasswordField();

        styleTextField(
                passwordField
        );

        passwordField.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        loginPanel.add(
                passwordField
        );


        centerPanel.add(
                loginPanel
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // BUTTON AREA

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                20
                        )
                );

        buttonPanel.setBackground(
                BACKGROUND
        );


        loginButton =
                new JButton(
                        "LOGIN"
                );

        exitButton =
                new JButton(
                        "EXIT"
                );


        styleButton(
                loginButton,
                PRIMARY
        );

        styleButton(
                exitButton,
                SECONDARY
        );


        buttonPanel.add(
                loginButton
        );

        buttonPanel.add(
                exitButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        add(
                mainPanel
        );

        // ACTIONS

        loginButton.addActionListener(
                e -> login()
        );


        exitButton.addActionListener(
                e -> System.exit(0)
        );


        passwordField.addActionListener(
                e -> login()
        );
    }

    // TEXT FIELD STYLE

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        field.setForeground(
                DARK
        );

        field.setBackground(
                WHITE
        );

        field.setCaretColor(
                PRIMARY
        );

        field.setPreferredSize(
                new Dimension(
                        350,
                        42
                )
        );

        field.setMaximumSize(
                new Dimension(
                        350,
                        42
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );
    }

    // BUTTON STYLE
    
    private void styleButton(
            JButton button,
            Color color
    ) {

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                PRIMARY_DARK,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                10,
                                18,
                                10,
                                18
                        )
                )
        );

        button.setBorderPainted(
                true
        );

        button.setOpaque(
                true
        );

        button.setPreferredSize(
                new Dimension(
                        160,
                        45
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // LOGIN FUNCTION
    
    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();


        String password =
                new String(
                        passwordField
                                .getPassword()
                );


        if (
                username.isEmpty()
                        ||
                password.isEmpty()
        ) {

            JOptionPane.showMessageDialog(

                    this,

                    "Please enter username and password.",

                    "Login Required",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        try {

            User user =
                    userService.login(
                            username,
                            password
                    );


            if (
                    user != null
            ) {

                JOptionPane.showMessageDialog(

                        this,

                        "Login successful!",

                        "Welcome",

                        JOptionPane.INFORMATION_MESSAGE
                );


                dispose();


                new DashboardGUI(
                        user
                ).setVisible(true);


            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Invalid username or password.",

                        "Login Failed",

                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (
                Exception ex
        ) {

            JOptionPane.showMessageDialog(

                    this,

                    "Database error:\n"
                            + ex.getMessage(),

                    "Connection Error",

                    JOptionPane.ERROR_MESSAGE
            );


            ex.printStackTrace();
        }
    }

    // MAIN METHOD
    
    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    new LoginGUI()
                            .setVisible(true);
                }
        );
    }
}