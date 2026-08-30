package parking.gui;

import parking.model.User;
import parking.dao.ParkingSlotDAO;
import parking.model.ParkingSlot;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardGUI extends JFrame {

    private final User loggedInUser;

    private final Color PRIMARY =
            new Color(22, 55, 92);

    private final Color SECONDARY =
            new Color(41, 128, 185);

    private final Color BACKGROUND =
            new Color(240, 244, 248);

    private final Color BUTTON_COLOR =
            Color.WHITE;

    private final Color BUTTON_TEXT =
            new Color(35, 45, 55);

    private final Color WHITE =
            Color.WHITE;


    // =========================
    // CONSTRUCTOR
    // =========================

    public DashboardGUI(User user) {

        this.loggedInUser = user;

        setTitle(
                "Smart Parking System - Dashboard"
        );

        setSize(
                1000,
                650
        );

        setMinimumSize(
                new Dimension(
                        900,
                        600
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();
    }


    // =========================
    // CREATE GUI
    // =========================

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                PRIMARY
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        25,
                        40
                )
        );


        JLabel titleLabel =
                new JLabel(
                        "SMART PARKING SYSTEM"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setForeground(
                WHITE
        );


        String userName =
                loggedInUser.getName();

        if (userName == null ||
                userName.trim().isEmpty()) {

            userName =
                    loggedInUser.getUsername();
        }


        JLabel userLabel =
                new JLabel(
                        "Welcome, "
                                + userName
                                + "  |  "
                                + loggedInUser.getRole()
                );

        userLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        userLabel.setForeground(
                new Color(
                        220,
                        230,
                        240
                )
        );


        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                userLabel,
                BorderLayout.EAST
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                30,
                                30
                        )
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        60,
                        100,
                        60,
                        100
                )
        );


        // =====================================================
        // BUTTONS
        // =====================================================

        JButton parkingButton =
                createMenuButton(
                        "PARKING MANAGEMENT"
                );


        JButton userButton =
                createMenuButton(
                        "USER MANAGEMENT"
                );


        JButton statusButton =
                createMenuButton(
                        "PARKING STATUS"
                );


        JButton logoutButton =
                createMenuButton(
                        "LOGOUT"
                );


        centerPanel.add(
                parkingButton
        );

        centerPanel.add(
                userButton
        );

        centerPanel.add(
                statusButton
        );

        centerPanel.add(
                logoutButton
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        JLabel footerLabel =
                new JLabel(
                        "Smart Parking Management System",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        footerLabel.setForeground(
                new Color(
                        100,
                        110,
                        120
                )
        );

        footerLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        10,
                        20,
                        10
                )
        );


        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );


        add(mainPanel);


        // =====================================================
        // PARKING MANAGEMENT BUTTON
        // =====================================================

        parkingButton.addActionListener(
                e -> openParkingManagement()
        );


        // =====================================================
        // USER MANAGEMENT BUTTON
        // =====================================================

        userButton.addActionListener(
                e -> openUserManagement()
        );


        // =====================================================
        // PARKING STATUS BUTTON
        // =====================================================

        statusButton.addActionListener(
                e -> showParkingStatus()
        );


        // =====================================================
        // LOGOUT BUTTON
        // =====================================================

        logoutButton.addActionListener(
                e -> logout()
        );
    }


    // =====================================================
    // OPEN PARKING MANAGEMENT
    // =====================================================

    private void openParkingManagement() {

        try {

            ParkingManagementGUI parkingGUI =
                    new ParkingManagementGUI();

            parkingGUI.setVisible(true);

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to open Parking Management.\n\n"
                            + ex.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // OPEN USER MANAGEMENT
    // =====================================================

    private void openUserManagement() {

        try {

            UserManagementGUI userGUI =
                    new UserManagementGUI();

            userGUI.setVisible(true);

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to open User Management.\n\n"
                            + ex.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // CREATE MENU BUTTON
    // =====================================================

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );


        button.setForeground(
                BUTTON_TEXT
        );


        button.setBackground(
                BUTTON_COLOR
        );


        button.setFocusPainted(
                false
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                SECONDARY,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );


        return button;
    }


    // =====================================================
    // PARKING STATUS
    // =====================================================

    private void showParkingStatus() {

        try {

            // Get current data directly from database

            ParkingSlotDAO parkingSlotDAO =
                    new ParkingSlotDAO();


            List<ParkingSlot> slots =
                    parkingSlotDAO.getAllSlots();


            // =================================================
            // NO DATA
            // =================================================

            if (
                    slots == null ||
                    slots.isEmpty()
            ) {

                JOptionPane.showMessageDialog(

                        this,

                        "No parking slots found in the database.\n\n"
                                + "Please add parking slots first.",

                        "Parking Status",

                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            // =================================================
            // COUNT STATUS
            // =================================================

            int available =
                    0;

            int occupied =
                    0;

            int other =
                    0;


            StringBuilder status =
                    new StringBuilder();


            status.append(
                    "CURRENT PARKING STATUS\n"
            );

            status.append(
                    "================================\n\n"
            );


            // =================================================
            // DISPLAY EACH SLOT
            // =================================================

            for (
                    ParkingSlot slot : slots
            ) {

                String slotStatus =
                        slot.getStatus();


                if (
                        slotStatus != null &&
                        slotStatus.equalsIgnoreCase(
                                "Available"
                        )
                ) {

                    available++;

                } else if (
                        slotStatus != null &&
                        slotStatus.equalsIgnoreCase(
                                "Occupied"
                        )
                ) {

                    occupied++;

                } else {

                    other++;
                }


                status.append(
                        "Slot "
                                + slot.getSlotNumber()
                                + "   |   "
                                + slot.getSlotType()
                                + "   |   "
                                + slotStatus
                                + "\n"
                );
            }


            // =================================================
            // SUMMARY
            // =================================================

            status.append(
                    "\n--------------------------------\n"
            );


            status.append(
                    "Total Slots     : "
                            + slots.size()
                            + "\n"
            );


            status.append(
                    "Available Slots : "
                            + available
                            + "\n"
            );


            status.append(
                    "Occupied Slots  : "
                            + occupied
                            + "\n"
            );


            if (other > 0) {

                status.append(
                        "Other Status    : "
                                + other
                                + "\n"
                );
            }


            status.append(
                    "\nData loaded from database."
            );


            // =================================================
            // SHOW STATUS
            // =================================================

            JTextArea textArea =
                    new JTextArea(
                            status.toString()
                    );


            textArea.setEditable(
                    false
            );


            textArea.setFont(
                    new Font(
                            "Consolas",
                            Font.PLAIN,
                            14
                    )
            );


            textArea.setBackground(
                    Color.WHITE
            );


            textArea.setBorder(
                    BorderFactory.createEmptyBorder(
                            10,
                            10,
                            10,
                            10
                    )
            );


            JScrollPane scrollPane =
                    new JScrollPane(
                            textArea
                    );


            scrollPane.setPreferredSize(
                    new Dimension(
                            500,
                            350
                    )
            );


            // =================================================
            // REFRESH BUTTON
            // =================================================

            JButton refreshButton =
                    new JButton(
                            "REFRESH"
                    );


            refreshButton.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            13
                    )
            );


            refreshButton.setForeground(
                    WHITE
            );


            refreshButton.setBackground(
                    SECONDARY
            );


            refreshButton.setFocusPainted(
                    false
            );


            refreshButton.setBorderPainted(
                    false
            );


            /*
             * Clicking REFRESH closes the current
             * status window and loads the latest
             * database values again.
             */

            refreshButton.addActionListener(
                    e -> {

                        Window window =
                                SwingUtilities
                                        .getWindowAncestor(
                                                refreshButton
                                        );

                        if (window != null) {

                            window.dispose();
                        }

                        showParkingStatus();
                    }
            );


            JPanel buttonPanel =
                    new JPanel(
                            new FlowLayout(
                                    FlowLayout.CENTER
                            )
                    );


            buttonPanel.add(
                    refreshButton
            );


            JPanel panel =
                    new JPanel(
                            new BorderLayout(
                                    10,
                                    10
                            )
                    );


            panel.setBorder(
                    BorderFactory.createEmptyBorder(
                            10,
                            10,
                            10,
                            10
                    )
            );


            panel.add(
                    scrollPane,
                    BorderLayout.CENTER
            );


            panel.add(
                    buttonPanel,
                    BorderLayout.SOUTH
            );


            JOptionPane.showMessageDialog(

                    this,

                    panel,

                    "Parking Status",

                    JOptionPane.PLAIN_MESSAGE
            );


        } catch (Exception e) {

            e.printStackTrace();


            JOptionPane.showMessageDialog(

                    this,

                    "Unable to load parking status.\n\n"
                            + e.getMessage(),

                    "Database Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // LOGOUT
    // =====================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to logout?",

                        "Logout",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.QUESTION_MESSAGE
                );


        if (
                choice ==
                        JOptionPane.YES_OPTION
        ) {

            dispose();


            new LoginGUI()
                    .setVisible(true);
        }
    }
}