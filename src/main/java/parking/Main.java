package parking;

import parking.gui.LoginGUI;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) 
    {

        // Run the GUI on the Swing Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            try {
                // Use the system's default professional look
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Open the Login GUI
            LoginGUI loginGUI = new LoginGUI();

            loginGUI.setVisible(true);
        });
    }
}