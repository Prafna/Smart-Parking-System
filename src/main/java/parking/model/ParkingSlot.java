package parking.model;

public class ParkingSlot {
    //attributes
    private int slotId;
    private String slotNumber;
    private String slotType;
    private String status;

    //Constructor
    public ParkingSlot(int slotId, String slotNumber, String slotType, String status)
    {
        this.slotId = slotId;
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.status = status;
    }

    public int getSlotId()
    {
        return slotId;
    }
    public String getSlotNumber()
    {
        return slotNumber;
    }
    public String getSlotType()
    {
        return slotType;
    }
    public String getStatus()
    {
        return status;
    }
    // changes the status of the parking slot
    public void setStatus(String status)
    {
        this.status = status;
    }
    //check the parking slot is available or not
    public boolean isAvailable()
    {
        return "Available".equalsIgnoreCase(status);
    }
    //check if the vehicle type is suitable for the slot
    public boolean isSuitableFor(String vehicleType)
    {
        return slotType.equalsIgnoreCase(vehicleType) && isAvailable();
    }
    //this method controls hoe the object is displayed
    @Override
    public String toString()
    {
        return slotNumber + " - " + slotType + " - " + status;
    }
}
