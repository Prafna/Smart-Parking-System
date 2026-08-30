package parking.gui;

import parking.dao.ParkingSessionDAO;
import parking.model.ParkingSession;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParkingSessionGUI extends JFrame {

    private ParkingSessionDAO parkingSessionDAO;
    private JTextArea sessionArea;

    public ParkingSessionGUI() {

        parkingSessionDAO =
                new ParkingSessionDAO();

        setTitle("Parking Sessions");

        setSize(900, 550);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();
    }


    private void createGUI() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel(
                        "PARKING SESSIONS",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );


        // =========================
        // SESSION AREA
        // =========================

        sessionArea =
                new JTextArea();

        sessionArea.setEditable(false);

        sessionArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        sessionArea
                );


        // =========================
        // REFRESH BUTTON
        // =========================

        JButton refreshButton =
                new JButton(
                        "Refresh Sessions"
                );

        refreshButton.addActionListener(
                e -> loadSessions()
        );


        // =========================
        // ADD COMPONENTS
        // =========================

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        panel.add(
                refreshButton,
                BorderLayout.SOUTH
        );


        add(panel);


        // Load sessions when window opens
        loadSessions();
    }


    // =========================
    // LOAD SESSIONS
    // =========================

    private void loadSessions() {

        sessionArea.setText("");

        try {

            List<ParkingSession> sessions =
                    parkingSessionDAO
                            .getAllSessions();


            if (
                    sessions == null
                    || sessions.isEmpty()
            ) {

                sessionArea.setText(
                        "No parking sessions found."
                );

                return;
            }


            for (
                    ParkingSession session :
                    sessions
            ) {

                sessionArea.append(
                        "Session ID: "
                        + session.getSessionId()
                        + "\n"
                        + "Vehicle ID: "
                        + session.getVehicleId()
                        + "\n"
                        + "Slot ID: "
                        + session.getSlotId()
                        + "\n"
                        + "Entry Time: "
                        + session.getEntryTime()
                        + "\n"
                        + "Exit Time: "
                        + getExitTime(session)
                        + "\n"
                        + "Duration: "
                        + getDuration(session)
                        + "\n"
                        + "Fee: "
                        + getFee(session)
                        + "\n"
                        + "Status: "
                        + session.getSessionStatus()
                        + "\n"
                        + "----------------------------------------\n"
                );
            }


        } catch (Exception e) {

            e.printStackTrace();

            sessionArea.setText(
                    "Error loading parking sessions.\n\n"
                    + e.getMessage()
            );
        }
    }


    private String getExitTime(
            ParkingSession session
    ) {

        if (
                session.getExitTime() == null
        ) {

            return "Not exited";
        }

        return session.getExitTime().toString();
    }


    private String getDuration(
            ParkingSession session
    ) {

        if (
                session.getExitTime() == null
        ) {

            return "Active";
        }

        return session.getDurationMinutes()
                + " minutes";
    }


    private String getFee(
            ParkingSession session
    ) {

        if (
                session.getExitTime() == null
        ) {

            return "Not calculated";
        }

        return String.format(
                "Rs. %.2f",
                session.getFee()
        );
    }
}
