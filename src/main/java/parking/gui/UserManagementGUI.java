package parking.gui;

import parking.model.User;
import parking.service.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementGUI extends JFrame {

    private JTextField userIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JComboBox<String> roleComboBox;

    private JTable userTable;
    private DefaultTableModel tableModel;

    private JLabel totalUsersLabel;
    private JLabel adminUsersLabel;
    private JLabel staffUsersLabel;

    private final UserService userService;

    // =========================
    // COLORS
    // =========================

    private final Color NAVY =
            new Color(20, 45, 75);

    private final Color BLUE =
            new Color(35, 120, 200);

    private final Color BACKGROUND =
            new Color(244, 247, 251);

    private final Color WHITE =
            Color.WHITE;

    private final Color TEXT =
            new Color(35, 45, 55);

    private final Color GRAY =
            new Color(110, 120, 130);

    private final Color GREEN =
            new Color(40, 150, 90);

    private final Color RED =
            new Color(205, 65, 65);

    private final Color BORDER =
            new Color(220, 225, 232);


    // =========================
    // CONSTRUCTOR
    // =========================

    public UserManagementGUI() {

        userService = new UserService();

        setTitle(
                "Smart Parking System - User Management"
        );

        setSize(1150, 720);

        setMinimumSize(
                new Dimension(1000, 650)
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();

        // Load users when opening
        loadUsers();
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
        // SIDEBAR
        // =====================================================

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(210, 0)
        );

        sidebar.setBackground(
                NAVY
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(
                        30,
                        20,
                        25,
                        20
                )
        );


        JLabel logo =
                new JLabel("PARKING");

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        logo.setForeground(
                WHITE
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel system =
                new JLabel("MANAGEMENT");

        system.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        system.setForeground(
                new Color(
                        185,
                        205,
                        225
                )
        );

        system.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        sidebar.add(logo);

        sidebar.add(
                Box.createVerticalStrut(3)
        );

        sidebar.add(system);

        sidebar.add(
                Box.createVerticalStrut(45)
        );


        JLabel menuTitle =
                new JLabel("ADMINISTRATION");

        menuTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        menuTitle.setForeground(
                new Color(
                        150,
                        175,
                        200
                )
        );

        menuTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(menuTitle);

        sidebar.add(
                Box.createVerticalStrut(12)
        );


        JButton userMenu =
                createSideButton(
                        "  USER MANAGEMENT"
                );

        userMenu.setBackground(
                BLUE
        );

        sidebar.add(userMenu);


        sidebar.add(
                Box.createVerticalStrut(10)
        );


        JButton closeButton =
                createSideButton(
                        "  CLOSE"
                );

        closeButton.addActionListener(
                e -> dispose()
        );

        sidebar.add(closeButton);


        sidebar.add(
                Box.createVerticalGlue()
        );


        JLabel footer =
                new JLabel(
                        "<html><center>Smart Parking<br>System</center></html>"
                );

        footer.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        footer.setForeground(
                new Color(
                        150,
                        170,
                        190
                )
        );

        footer.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(footer);


        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );


        // =====================================================
        // CONTENT
        // =====================================================

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);


        JPanel heading =
                new JPanel();

        heading.setOpaque(false);

        heading.setLayout(
                new BoxLayout(
                        heading,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "User Management"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                TEXT
        );


        JLabel subtitle =
                new JLabel(
                        "Manage users, roles and system access"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                GRAY
        );


        heading.add(title);

        heading.add(
                Box.createVerticalStrut(5)
        );

        heading.add(subtitle);


        header.add(
                heading,
                BorderLayout.WEST
        );


        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        JButton refreshButton =
                createActionButton(
                        "REFRESH",
                        BLUE
                );


        /*
         * IMPORTANT:
         * This action definitely runs when the button
         * is clicked.
         */

        refreshButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Refreshing user list...",
                            "Refresh",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    loadUsers();
                }
        );


        header.add(
                refreshButton,
                BorderLayout.EAST
        );


        content.add(
                header,
                BorderLayout.NORTH
        );


        // =====================================================
        // CENTER
        // =====================================================

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        center.setOpaque(false);


        // =====================================================
        // STATISTICS
        // =====================================================

        JPanel stats =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        stats.setOpaque(false);


        JPanel totalCard =
                createStatCard(
                        "TOTAL USERS",
                        "0",
                        BLUE
                );


        JPanel adminCard =
                createStatCard(
                        "ADMIN USERS",
                        "0",
                        NAVY
                );


        JPanel staffCard =
                createStatCard(
                        "STAFF USERS",
                        "0",
                        GREEN
                );


        totalUsersLabel =
                findValueLabel(
                        totalCard
                );


        adminUsersLabel =
                findValueLabel(
                        adminCard
                );


        staffUsersLabel =
                findValueLabel(
                        staffCard
                );


        stats.add(totalCard);

        stats.add(adminCard);

        stats.add(staffCard);


        center.add(
                stats,
                BorderLayout.NORTH
        );


        // =====================================================
        // LOWER AREA
        // =====================================================

        JPanel lower =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        lower.setOpaque(false);


        // =====================================================
        // USER FORM
        // =====================================================

        JPanel formCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        formCard.setBackground(
                WHITE
        );

        formCard.setPreferredSize(
                new Dimension(
                        320,
                        0
                )
        );

        formCard.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );


        JLabel formTitle =
                new JLabel(
                        "USER DETAILS"
                );

        formTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        formTitle.setForeground(
                TEXT
        );


        formCard.add(
                formTitle,
                BorderLayout.NORTH
        );


        JPanel form =
                new JPanel(
                        new GridLayout(
                                8,
                                1,
                                0,
                                7
                        )
                );

        form.setOpaque(false);


        // USER ID

        form.add(
                createLabel(
                        "USER ID"
                )
        );


        userIdField =
                createTextField();


        form.add(
                userIdField
        );


        // USERNAME

        form.add(
                createLabel(
                        "USERNAME"
                )
        );


        usernameField =
                createTextField();


        form.add(
                usernameField
        );


        // PASSWORD

        form.add(
                createLabel(
                        "PASSWORD"
                )
        );


        passwordField =
                new JPasswordField();


        styleField(
                passwordField
        );


        form.add(
                passwordField
        );


        // ROLE

        form.add(
                createLabel(
                        "ROLE"
                )
        );


        roleComboBox =
                new JComboBox<>(
                        new String[]{
                                "Admin",
                                "Staff"
                        }
                );


        styleCombo(
                roleComboBox
        );


        form.add(
                roleComboBox
        );


        formCard.add(
                form,
                BorderLayout.CENTER
        );


        // =====================================================
        // FORM BUTTONS
        // =====================================================

        JPanel formButtons =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                8,
                                8
                        )
                );

        formButtons.setOpaque(false);


        JButton addButton =
                createActionButton(
                        "ADD USER",
                        GREEN
                );


        JButton updateButton =
                createActionButton(
                        "UPDATE",
                        BLUE
                );


        JButton deleteButton =
                createActionButton(
                        "DELETE",
                        RED
                );


        JButton clearButton =
                createActionButton(
                        "CLEAR",
                        GRAY
                );


        formButtons.add(addButton);

        formButtons.add(updateButton);

        formButtons.add(deleteButton);

        formButtons.add(clearButton);


        formCard.add(
                formButtons,
                BorderLayout.SOUTH
        );


        lower.add(
                formCard,
                BorderLayout.WEST
        );


        // =====================================================
        // TABLE CARD
        // =====================================================

        JPanel tableCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        tableCard.setBackground(
                WHITE
        );

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );


        JPanel tableHeader =
                new JPanel(
                        new BorderLayout()
                );

        tableHeader.setOpaque(false);


        JLabel tableTitle =
                new JLabel(
                        "REGISTERED USERS"
                );

        tableTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        tableTitle.setForeground(
                TEXT
        );


        JButton searchButton =
                createActionButton(
                        "SEARCH BY ID",
                        BLUE
                );


        tableHeader.add(
                tableTitle,
                BorderLayout.WEST
        );


        tableHeader.add(
                searchButton,
                BorderLayout.EAST
        );


        tableCard.add(
                tableHeader,
                BorderLayout.NORTH
        );


        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "ID",
                "USERNAME",
                "ROLE"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        userTable =
                new JTable(
                        tableModel
                );


        userTable.setRowHeight(
                38
        );


        // Clear readable font for usernames
        userTable.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );


        userTable.setForeground(
                TEXT
        );


        userTable.setBackground(
                WHITE
        );


        userTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        userTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );


        userTable.getTableHeader()
                .setBackground(
                        NAVY
                );


        userTable.getTableHeader()
                .setForeground(
                        WHITE
                );


        userTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                38
                        )
                );


        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();


        renderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        userTable.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        renderer
                );


        JScrollPane tableScroll =
                new JScrollPane(
                        userTable
                );


        tableScroll.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );


        tableCard.add(
                tableScroll,
                BorderLayout.CENTER
        );


        lower.add(
                tableCard,
                BorderLayout.CENTER
        );


        center.add(
                lower,
                BorderLayout.CENTER
        );


        content.add(
                center,
                BorderLayout.CENTER
        );


        mainPanel.add(
                content,
                BorderLayout.CENTER
        );


        add(mainPanel);


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        addButton.addActionListener(
                e -> addUser()
        );


        updateButton.addActionListener(
                e -> updateUser()
        );


        deleteButton.addActionListener(
                e -> deleteUser()
        );


        clearButton.addActionListener(
                e -> clearFields()
        );


        searchButton.addActionListener(
                e -> searchUser()
        );


        // Select user from table

        userTable.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (
                                    !e.getValueIsAdjusting()
                            ) {

                                loadSelectedUser();
                            }
                        }
                );
    }


    // =====================================================
    // LOAD USERS
    // =====================================================

    private void loadUsers() {

        try {

            List<User> users =
                    userService.getAllUsers();


            // Remove old rows

            tableModel.setRowCount(0);


            int adminCount = 0;

            int staffCount = 0;


            if (users != null) {

                for (User user : users) {

                    tableModel.addRow(
                            new Object[]{
                                    user.getUserId(),
                                    user.getUsername(),
                                    user.getRole()
                            }
                    );


                    if (
                            "Admin".equalsIgnoreCase(
                                    user.getRole()
                            )
                    ) {

                        adminCount++;
                    }


                    if (
                            "Staff".equalsIgnoreCase(
                                    user.getRole()
                            )
                    ) {

                        staffCount++;
                    }
                }
            }


            // Update statistics

            totalUsersLabel.setText(
                    String.valueOf(
                            users == null
                                    ? 0
                                    : users.size()
                    )
            );


            adminUsersLabel.setText(
                    String.valueOf(
                            adminCount
                    )
            );


            staffUsersLabel.setText(
                    String.valueOf(
                            staffCount
                    )
            );


            // Force screen refresh

            tableModel.fireTableDataChanged();

            userTable.revalidate();

            userTable.repaint();


        } catch (Exception ex) {

            ex.printStackTrace();


            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load users.\n\n"
                            + ex.getMessage(),

                    "Database Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // ADD USER
    // =====================================================

    private void addUser() {

        String username =
                usernameField
                        .getText()
                        .trim();


        String password =
                new String(
                        passwordField
                                .getPassword()
                );


        String role =
                roleComboBox
                        .getSelectedItem()
                        .toString();


        if (
                username.isEmpty()
                        ||
                password.isEmpty()
        ) {

            showError(
                    "Username and password cannot be empty."
            );

            return;
        }


        try {

            User user =
                    new User();


            user.setUsername(
                    username
            );


            user.setPassword(
                    password
            );


            user.setRole(
                    role
            );


            boolean result =
                    userService.addUser(
                            user
                    );


            if (result) {

                showSuccess(
                        "User added successfully."
                );


                clearFields();


                loadUsers();


            } else {

                showError(
                        "Failed to add user."
                );
            }


        } catch (Exception ex) {

            showError(
                    "Error: "
                            + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }


    // =====================================================
    // SEARCH USER
    // =====================================================

    private void searchUser() {

        String idText =
                userIdField
                        .getText()
                        .trim();


        if (idText.isEmpty()) {

            showError(
                    "Enter a User ID first."
            );

            return;
        }


        try {

            int userId =
                    Integer.parseInt(
                            idText
                    );


            User user =
                    userService.getUserById(
                            userId
                    );


            if (user != null) {

                usernameField.setText(
                        user.getUsername()
                );


                passwordField.setText(
                        user.getPassword()
                );


                roleComboBox.setSelectedItem(
                        user.getRole()
                );


                // Find user in table

                for (
                        int i = 0;
                        i < tableModel.getRowCount();
                        i++
                ) {

                    int tableId =
                            Integer.parseInt(
                                    tableModel
                                            .getValueAt(
                                                    i,
                                                    0
                                            )
                                            .toString()
                            );


                    if (
                            tableId == userId
                    ) {

                        userTable
                                .setRowSelectionInterval(
                                        i,
                                        i
                                );


                        userTable
                                .scrollRectToVisible(
                                        userTable
                                                .getCellRect(
                                                        i,
                                                        0,
                                                        true
                                                )
                                );


                        break;
                    }
                }


            } else {

                showError(
                        "No user found with ID "
                                + userId
                );
            }


        } catch (
                NumberFormatException ex
        ) {

            showError(
                    "User ID must be a number."
            );


        } catch (Exception ex) {

            showError(
                    "Error: "
                            + ex.getMessage()
            );
        }
    }


    // =====================================================
    // UPDATE USER
    // =====================================================

    private void updateUser() {

        String idText =
                userIdField
                        .getText()
                        .trim();


        if (idText.isEmpty()) {

            showError(
                    "Enter a User ID first."
            );

            return;
        }


        try {

            int userId =
                    Integer.parseInt(
                            idText
                    );


            User user =
                    new User();


            user.setUserId(
                    userId
            );


            user.setUsername(
                    usernameField
                            .getText()
                            .trim()
            );


            user.setPassword(
                    new String(
                            passwordField
                                    .getPassword()
                    )
            );


            user.setRole(
                    roleComboBox
                            .getSelectedItem()
                            .toString()
            );


            boolean result =
                    userService.updateUser(
                            user
                    );


            if (result) {

                showSuccess(
                        "User updated successfully."
                );


                loadUsers();


            } else {

                showError(
                        "Failed to update user."
                );
            }


        } catch (
                NumberFormatException ex
        ) {

            showError(
                    "User ID must be a number."
            );


        } catch (Exception ex) {

            showError(
                    "Error: "
                            + ex.getMessage()
            );
        }
    }


    // =====================================================
    // DELETE USER
    // =====================================================

    private void deleteUser() {

        String idText =
                userIdField
                        .getText()
                        .trim();


        if (idText.isEmpty()) {

            showError(
                    "Enter a User ID first."
            );

            return;
        }


        try {

            int userId =
                    Integer.parseInt(
                            idText
                    );


            int choice =
                    JOptionPane.showConfirmDialog(

                            this,

                            "Are you sure you want to delete User ID "
                                    + userId
                                    + "?",

                            "Confirm Delete",

                            JOptionPane.YES_NO_OPTION,

                            JOptionPane.WARNING_MESSAGE
                    );


            if (
                    choice !=
                            JOptionPane.YES_OPTION
            ) {

                return;
            }


            boolean result =
                    userService.deleteUser(
                            userId
                    );


            if (result) {

                showSuccess(
                        "User deleted successfully."
                );


                clearFields();


                loadUsers();


            } else {

                showError(
                        "Failed to delete user."
                );
            }


        } catch (
                NumberFormatException ex
        ) {

            showError(
                    "User ID must be a number."
            );


        } catch (Exception ex) {

            showError(
                    "Error: "
                            + ex.getMessage()
            );
        }
    }


    // =====================================================
    // SELECT USER
    // =====================================================

    private void loadSelectedUser() {

        int row =
                userTable
                        .getSelectedRow();


        if (row < 0) {

            return;
        }


        userIdField.setText(
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString()
        );


        usernameField.setText(
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString()
        );


        roleComboBox.setSelectedItem(
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString()
        );
    }


    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        userIdField.setText("");

        usernameField.setText("");

        passwordField.setText("");

        roleComboBox.setSelectedIndex(0);

        userTable.clearSelection();
    }


    // =====================================================
    // SIDEBAR BUTTON
    // =====================================================

    private JButton createSideButton(
            String text
    ) {

        JButton button =
                new JButton(text);


        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );


        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );


        button.setForeground(
                WHITE
        );


        button.setBackground(
                new Color(
                        30,
                        60,
                        90
                )
        );


        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =====================================================
    // STATISTICS CARD
    // =====================================================

    private JPanel createStatCard(
            String title,
            String value,
            Color accent
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );


        card.setBackground(
                WHITE
        );


        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );


        JPanel bar =
                new JPanel();


        bar.setPreferredSize(
                new Dimension(
                        5,
                        0
                )
        );


        bar.setBackground(
                accent
        );


        JPanel text =
                new JPanel();


        text.setOpaque(false);


        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );


        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );


        titleLabel.setForeground(
                GRAY
        );


        JLabel valueLabel =
                new JLabel(
                        value
                );


        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );


        valueLabel.setForeground(
                TEXT
        );


        text.add(
                titleLabel
        );


        text.add(
                Box.createVerticalStrut(5)
        );


        text.add(
                valueLabel
        );


        card.add(
                bar,
                BorderLayout.WEST
        );


        card.add(
                text,
                BorderLayout.CENTER
        );


        return card;
    }


    private JLabel findValueLabel(
            JPanel card
    ) {

        JPanel text =
                (JPanel)
                        card.getComponent(
                                1
                        );


        return (JLabel)
                text.getComponent(
                        2
                );
    }

    // FORM HELPERS

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );


        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );


        label.setForeground(
                GRAY
        );


        return label;
    }


    private JTextField createTextField() {

        JTextField field =
                new JTextField();


        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );


        field.setForeground(
                TEXT
        );


        field.setBackground(
                WHITE
        );


        // Makes typed characters and underscore visible

        field.setCaretColor(
                TEXT
        );


        field.setOpaque(true);


        field.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );


        return field;
    }


    private void styleField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );


        field.setForeground(
                TEXT
        );


        field.setBackground(
                WHITE
        );


        field.setCaretColor(
                TEXT
        );


        field.setOpaque(true);


        field.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );
    }


    private void styleCombo(
            JComboBox<String> combo
    ) {

        combo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        combo.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );
    }

    // ACTION BUTTON

    private JButton createActionButton(
            String text,
            Color color
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );


        button.setForeground(
                WHITE
        );


        button.setBackground(
                color
        );


        button.setFocusPainted(false);


        button.setBorderPainted(false);


        button.setOpaque(true);


        button.setContentAreaFilled(true);


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.setPreferredSize(
                new Dimension(
                        110,
                        38
                )
        );


        return button;
    }

    // SUCCESS MESSAGE

    private void showSuccess(
            String message
    ) {

        JOptionPane.showMessageDialog(

                this,

                message,

                "Success",

                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ERROR MESSAGE

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(

                this,

                message,

                "Error",

                JOptionPane.ERROR_MESSAGE
        );
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    new UserManagementGUI()
                            .setVisible(true);

                }
        );
    }
}