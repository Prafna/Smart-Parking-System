package parking.dao;
import parking.model.ParkingSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ParkingSessionDAO {
    //Create a new parking session
    public boolean createSession(ParkingSession session) {

    String sql =
            "INSERT INTO dbo.ParkingSession " +
            "(VehicleID, SlotID, EntryTime, SessionStatus) " +
            "VALUES (?, ?, ?, ?)";

    try (
        Connection connection =
                DatabaseConnection.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(sql)
    ) {

        statement.setInt(
                1,
                session.getVehicleId()
        );

        statement.setInt(
                2,
                session.getSlotId()
        );

        statement.setTimestamp(
                3,
                Timestamp.valueOf(
                        session.getEntryTime()
                )
        );

        statement.setString(
                4,
                session.getSessionStatus()
        );

        int rowsInserted =
                statement.executeUpdate();

        return rowsInserted > 0;

    } catch (SQLException e) {

        System.out.println(
                "Error creating parking session:"
        );

        e.printStackTrace();

        return false;
    }
}
    //Getting parking session by session ID
    public ParkingSession getSessionById(int sessionId){
        String sql = "SELECT SessionID, VehicleID,SlotID, " +
                    "EntryTime, ExitTime, DurationMinutes, Fee, SessionStatus " +
                    "FROM ParkingSession " +
                    "WHERE SessionID = ? ";
                
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sessionId);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return createSessionFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding parking session.");
            e.printStackTrace();
        }
        return null;
    }
    //Get active session for a vehicle
    public ParkingSession getActiveSessionByVehicle(int vehicleId) {
        String sql = "SELECT SessionID, VehicleID, SlotID, " +
                    "EntryTime, ExitTime, DurationMinutes, FEE, SessionStatus " +
                    "FROM ParkingSession " +
                    "WHERE VehicleID = ? " +
                    "AND SessionStatus = 'Active'";
         try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, vehicleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createSessionFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding active parking session.");
            e.printStackTrace();
        }
        return null;
    }   
    //Complete a parking session
    public boolean completeSession(
        int sessionId,
        java.time.LocalDateTime ExitTime,
        int durationMinutes,
        double fee) {
        String sql = "UPDATE ParkingSession " +
                    "SET ExitTime = ?, " +
                    "DurationMinutes = ?, " +
                    "Fee = ?, " +
                    "SessionStatus = 'Completed' " +
                    "WHERE SessionID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, Timestamp.valueOf(ExitTime));
            statement.setInt(2, durationMinutes);
            statement.setDouble(3, fee);
            statement.setInt(4, sessionId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error completing parking session.");
            e.printStackTrace();
            return false;
        }
    }
    //Get all parking sessions
    public  List<ParkingSession> getAllSessions() {
        List<ParkingSession> sessions = new ArrayList<>();
        String sql = "SELECT SessionID, VehicleID, SlotID," +
                    "EntryTime, ExitTime,DurationMinutes, Fee, SessionStatus " +
                    "FROM ParkingSession " +
                    "ORDER BY SessionID";
        
         try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                ParkingSession session = createSessionFromResultSet(resultSet);
                sessions.add(session);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving parking sessions.");
            e.printStackTrace();
        }
        return sessions;
    }
    //Creates parking session object from database result
    private ParkingSession createSessionFromResultSet(
        ResultSet resultSet) throws SQLException {

            int sessionId = resultSet.getInt("SessionID");
            int vehicleId = resultSet.getInt("VehicleID");
            int slotId = resultSet.getInt("SlotID");

             Timestamp entryTimestamp = resultSet.getTimestamp("EntryTime");

            //Create parking session using constructor
            ParkingSession session = new ParkingSession(
                        sessionId,
                        vehicleId,
                        slotId,
                        entryTimestamp.toLocalDateTime()
            );
        //Check the session has been completed
        Timestamp exitTimestamp = resultSet.getTimestamp("ExitTime");

        if (exitTimestamp != null) {

        int durationMinutes = resultSet.getInt("DurationMinutes");
        double fee = resultSet.getDouble("Fee");

        session.completeSession(
        exitTimestamp.toLocalDateTime(),
        durationMinutes,
        fee
    );
}
        return session;
    }
}
