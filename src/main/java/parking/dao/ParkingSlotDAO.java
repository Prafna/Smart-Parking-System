package parking.dao;

import parking.model.ParkingSlot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ParkingSlotDAO {
    //Get all parking slots from database 
    public List<ParkingSlot> getAllSlots() {
        List<ParkingSlot> slots = new ArrayList<>();
        String sql = "SELECT SlotId, SlotNumber, SlotType, Status " +
                    "FROM ParkingSlots " + 
                    "ORDER BY SlotID ";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next())    {
                    ParkingSlot slot = new ParkingSlot(
                        resultSet.getInt("SlotID"),
                        resultSet.getString("SlotNumber"),
                        resultSet.getString("SlotType"),
                        resultSet.getString("Status")
                    );
                    slots.add(slot);
                }
            } 
             catch (SQLException e) {

              JOptionPane.showMessageDialog(
            null,
            "DATABASE ERROR:\n\n" + e.getMessage()
             );

            e.printStackTrace();
            }
                return slots;
    }
    
    //Getting parking slot using slotID
    public ParkingSlot getSlotById(int slotId) {
        String sql = "SELECT SlotID, SlotNumber, SlotType, Status " + 
                    "FROM ParkingSlots " +
                     "WHERE SlotID = ?";
    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)) {

        //Set the SlotID value
       statement.setInt(1, slotId);

        try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new ParkingSlot(
                    resultSet.getInt("SlotID"),
                    resultSet.getString("SlotNumber"),
                    resultSet.getString("SlotType"),
                    resultSet.getString("Status")
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Error finding parking slot.");
        e.printStackTrace();
    }
    return null;
    }
    //Get all available parking slots 
    public List<ParkingSlot> getAvailableSlots() {
        List<ParkingSlot> slots = new ArrayList<>();
        String sql = "SELECT SlotID, SlotNumber, SlotType, Status " +
                    "FROM ParkingSlots " +
                    "WHERE Status = 'Available' " +
                    "ORDER BY SlotID";
        
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                ParkingSlot slot = new ParkingSlot(
                    resultSet.getInt("SlotID"),
                    resultSet.getString("SlotNumber"),
                    resultSet.getString("SlotType"),
                    resultSet.getString("Status")
                );
                slots.add(slot);
            }
            } catch (SQLException e) {
                System.out.println("Error retrieving available parking slots,");
                e.printStackTrace();
            }
            return slots;
    }
    //Get available slots by vehicle type
    // Get available parking slots by vehicle type
public List<ParkingSlot> getAvailableSlotsByType(String vehicleType) {

    List<ParkingSlot> slots = new ArrayList<>();
    String sql = "SELECT SlotID, SlotNumber, SlotType, Status " +
                 "FROM ParkingSlots " +
                 "WHERE SlotType = ? " +
                 "AND Status = 'Available' " +
                 "ORDER BY SlotID";
    try (
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
        statement.setString(1, vehicleType);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ParkingSlot slot = new ParkingSlot(
                    resultSet.getInt("SlotID"),
                    resultSet.getString("SlotNumber"),
                    resultSet.getString("SlotType"),
                    resultSet.getString("Status")
                );
               slots.add(slot);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error finding slots by vehicle type.");
        e.printStackTrace();
    }
    return slots;
}
        public boolean updateSlotStatus(int slotId, String status) {
        String sql = "UPDATE ParkingSlots " +
                     "SET Status = ? " +
                     "WHERE SlotID = ?";
        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, status);
            statement.setInt(2, slotId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating parking slot status.");
            e.printStackTrace();
            return false;
        }
    }
}
