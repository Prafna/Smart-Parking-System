package parking.service;

import parking.dao.ParkingSessionDAO;
import parking.dao.ParkingSlotDAO;
import parking.model.ParkingSession;
import parking.model.ParkingSlot;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

public class ParkingService {

    
    private ParkingSlotDAO parkingSlotDAO;
    private ParkingSessionDAO parkingSessionDAO;

    // Constructor
    public ParkingService() {
        parkingSlotDAO = new ParkingSlotDAO();
        parkingSessionDAO = new ParkingSessionDAO();
    }

    // Get all parking slots
    public List<ParkingSlot> getAllSlots() {
        return parkingSlotDAO.getAllSlots();
    }

    // Get all available parking slots
    public List<ParkingSlot> getAvailableSlots() {
        return parkingSlotDAO.getAvailableSlots();
    }

    // Get available slots according to vehicle type
    public List<ParkingSlot> getAvailableSlotsByType(String vehicleType) {
        return parkingSlotDAO.getAvailableSlotsByType(vehicleType);
    }

    
    public boolean parkVehicle(
            int sessionId,
            int vehicleId,
            String vehicleType) {

        // Find available slots suitable for the vehicle
        List<ParkingSlot> availableSlots =
                parkingSlotDAO.getAvailableSlotsByType(vehicleType);

        // Check whether a slot is available
        if (availableSlots.isEmpty()) {
            System.out.println("No available parking slots.");
            return false;
        }

        ParkingSlot selectedSlot = availableSlots.get(0);

        ParkingSession session = new ParkingSession(
                sessionId,
                vehicleId,
                selectedSlot.getSlotId(),
                LocalDateTime.now()
        );
        boolean sessionCreated = parkingSessionDAO.createSession(session);

        // If session was successfully created change the status
        if (sessionCreated) {

            boolean slotUpdated = parkingSlotDAO.updateSlotStatus(
                            selectedSlot.getSlotId(),
                            "Occupied"
                    );

            return slotUpdated;
        }

        return false;
    }

    // Complete a parking session when the vehicle leaves
    public boolean completeParkingSession(int sessionId) {

        ParkingSession session = parkingSessionDAO.getSessionById(sessionId);

        // Check whether the session exists
        if (session == null) {
            System.out.println("Parking session not found.");
            return false;
        }

        // Check whether the session is already completed
        if (session.getSessionStatus().equals("Completed")) {
            System.out.println("Parking session is already completed.");
            return false;
        }

        // Get the current time as exit time
        LocalDateTime exitTime = LocalDateTime.now();

        // Calculate parking duration
        int durationMinutes = (int) Duration.between(
                        session.getEntryTime(),
                        exitTime
                        ).toMinutes();

        // Calculate parking fee
        double fee = calculateFee(durationMinutes);

        // Complete the session in the database
        boolean sessionCompleted = parkingSessionDAO.completeSession(
                            sessionId,
                            exitTime,
                            durationMinutes,
                            fee
                            );

        // If the session was completed, change the slot status back to Available
        if (sessionCompleted) {

            boolean slotUpdated = parkingSlotDAO.updateSlotStatus(
                            session.getSlotId(),
                            "Available"
                            );

            return slotUpdated;
        }

        return false;
    }

    // Calculate parking fee
    private double calculateFee(int durationMinutes) {

        // First hour = 100
        // Every additional hour = 50

        if (durationMinutes <= 60) {
            return 100.00;
        }

        int additionalMinutes = durationMinutes - 60;

        int additionalHours =
                (int) Math.ceil(additionalMinutes / 60.0);

        return 100.00 + (additionalHours * 50.00);
    }

    // Get a parking session using session ID
    public ParkingSession getSessionById(int sessionId) {
        return parkingSessionDAO.getSessionById(sessionId);
    }

    // Get active parking session for a vehicle
    public ParkingSession getActiveSessionByVehicle(int vehicleId) {
        return parkingSessionDAO.getActiveSessionByVehicle(vehicleId);
    }

    // Get all parking sessions
    public List<ParkingSession> getAllSessions() {
        return parkingSessionDAO.getAllSessions();
    }
}
