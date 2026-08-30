package parking.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingSession {
    private int sessionId;
    private int vehicleId;
    private int slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private int durationMinutes;
    private double fee;
    private String sessionStatus;
    
    //constructor
    public ParkingSession(int sessionId, int vehicleId, int slotId, LocalDateTime entryTime ){
        this.sessionId = sessionId;
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.entryTime = entryTime;

        this.sessionStatus = "Active";
    }

    public int getSessionId(){
        return sessionId;
    }
    public int getVehicleId(){
        return vehicleId;
    }
    public int getSlotId(){
        return slotId;
    }
    public LocalDateTime getEntryTime(){
        return entryTime;
    }
    public LocalDateTime getExitTime(){
        return exitTime;
    }
    public int getDurationMinutes(){
        return durationMinutes;
    }
    public double getFee(){
        return fee;
    }
    public String getSessionStatus(){
        return sessionStatus;
    }
    //this method called when vehicle exits
    public void completeSession(LocalDateTime exitTime, int durationMinutes, double fee){
        this.exitTime = exitTime;
        this.durationMinutes = durationMinutes;
        this.fee = fee;
    
    //the vehicle left the parking area
    this.sessionStatus = "Completed";
    }
    //calculation of parking hrs
    public int calculateDurationMinutes(){
        LocalDateTime endTime;

        if (exitTime != null){
            endTime = exitTime;
        } 
        else{
            endTime = LocalDateTime.now();
        }

        //calculate the difference between wntry and exit time
        return (int) Duration.between(
            entryTime, endTime).toMinutes();

        
    }

    
}
