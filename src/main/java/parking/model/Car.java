package parking.model;

public class Car extends Vehicle 
{
	public Car(int vehicleId, String vehicleNumber, String ownerName, String contactNumber) 
    {
		super(vehicleId, vehicleNumber, ownerName, contactNumber);
	} 

	//constructor used when registering a new car
	public Car(String vehicleNumber, String ownerName, String contactNumber) 
	{
		super(0, vehicleNumber, ownerName, contactNumber);
	}

	//implementation of the abstract method from the Vehicle class
	@Override
	public String getVehicleType() 
    {
		return "Car";
	}
}