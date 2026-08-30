package parking.model;

//abstract parent class for all vehicles
public abstract class Vehicle 
{
    //vehicle information
    private int vehicleId;
    private String vehicleNumber;
    private String ownerName;
    private String contactNumber;

    // Constructor
    public Vehicle(int vehicleId, String vehicleNumber, String ownerName, String contactNumber) 
    {
        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public int getVehicleId() 
    {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() 
    {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) 
    {
        this.vehicleNumber = vehicleNumber;
    }

    public String getOwnerName() 
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName) 
    {
        this.ownerName = ownerName;
    }

    public String getContactNumber() 
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    //each vehicle type must provide its own vehicle type
    public abstract String getVehicleType();

    //display vehicle information
    public void displayVehicleInfo()
    {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Vehicle Type: " + getVehicleType());
    }

}
