package parking.model;

public class Motorbike extends Vehicle 
{
    public Motorbike(int vehicleId, String vehicleNumber, String ownerName, String contactNumber) 
    {
        super(vehicleId, vehicleNumber, ownerName, contactNumber);
    }

    //constructor used when registering a new motorbike
    public Motorbike(String vehicleNumber, String ownerName, String contactNumber) 
    {
        super(0, vehicleNumber, ownerName, contactNumber);
    }

    //implementation of the abstract method from the Vehicle class
    @Override
    public String getVehicleType() 
    {
        return "Motorbike";
    }
}
