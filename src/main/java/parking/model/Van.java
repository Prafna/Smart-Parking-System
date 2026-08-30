package parking.model;

public class Van extends Vehicle 
{
    public Van(int vehicleId, String vehicleNumber, String ownerName, String contactNumber) 
    {
        super(vehicleId, vehicleNumber, ownerName, contactNumber);
    } 

    //constructor used when registering a new van
    public Van(String vehicleNumber, String ownerName, String contactNumber) 
    {
        super(0, vehicleNumber, ownerName, contactNumber);
    }

    //implementation of the abstract method from the Vehicle class
    @Override
    public String getVehicleType() 
    {
        return "Van";
    }
    
}
