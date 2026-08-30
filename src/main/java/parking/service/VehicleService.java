
package parking.service;

import parking.dao.VehicleDAO;
import parking.model.Vehicle;

import java.util.List;

//It communicates VehicleDAO to access the SQL Server database
public class VehicleService 
{
    private final VehicleDAO vehicleDAO;

    //constructor that creates the Vehicle DAO object when the VehicleService is created
    public VehicleService() 
    {
        vehicleDAO = new VehicleDAO();
    }

    //CREATE
    //Validates and adds a new vehicle to the database
    public boolean addVehicle(Vehicle vehicle)
    {
        //Make sure the object is not null
        if (vehicle == null)
        {
            System.out.println("Vehicle object cannot be null.");
            return false;
        }

        //check the vehicle number
        if (vehicle.getVehicleNumber() == null || vehicle.getVehicleNumber().isEmpty())
        {
            System.out.println("Vehicle number cannot be empty.");
            return false;
        }

        //check the owner's name
        if (vehicle.getOwnerName() == null || vehicle.getOwnerName().isEmpty())
        {
            System.out.println("Owner name cannot be empty.");
            return false;
        }

        //check the contact number
        if (vehicle.getContactNumber() == null || vehicle.getContactNumber().isEmpty())
        {
            System.out.println("Contact number cannot be empty.");
            return false;
        }

        //check wether the vehicle number already exist in the database
        if (vehicleDAO.getVehicleByNumber(vehicle.getVehicleNumber()) != null)
        {
            System.out.println("A vehicle with this number already exists.");
            return false;
        }

        //if all validations pass, add the vehicle to the database
        return vehicleDAO.addVehicle(vehicle);
    } 
    
    //READ
    //Returns all vehicles from the database
    public List<Vehicle> getAllVehicles()
    {
        return vehicleDAO.getAllVehicles();
    }

    //SEARCH
    //Searches for a vehicle in the database by its registration number
    public Vehicle searchVehicle(String vehicleNumber)
    {
        //validates the search value
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty())
        {
            System.out.println("Vehicle number cannot be empty.");
            return null;
        }

        //Ask the DAO to search for the vehicle in the database
        return vehicleDAO.getVehicleByNumber(vehicleNumber);
    }

    //UPDATE
    //updates and existing vehicle
    public boolean updateVehicle(Vehicle vehicle)
    {
        //Make sure the object is not null
        if (vehicle == null)
        {
            System.out.println("Vehicle object cannot be null.");
            return false;
        }

        //perform basic validation
        if (vehicle.getVehicleNumber() == null || vehicle.getVehicleNumber().trim().isEmpty())
        {
            System.out.println("Vehicle number cannot be empty.");
            return false;
        }

        //check the owner's name
        if (vehicle.getOwnerName() == null || vehicle.getOwnerName().isEmpty())
        {
            System.out.println("Owner name cannot be empty.");
            return false;
        }

        //check the contact number
        if (vehicle.getContactNumber() == null || vehicle.getContactNumber().trim().isEmpty())
        {
            System.out.println("Contact number cannot be empty.");
            return false;
        }

        //if all validations pass, update the vehicle in the database
        return vehicleDAO.updateVehicle(vehicle);
    }

    //DELETE
    //deletes a vehicle from the database by its ID
    public boolean deleteVehicle(int vehicleId)
    {
        //check if the vehicle ID is valid
        if (vehicleId <= 0)
        {
            System.out.println("Invalid vehicle ID.");
            return false;
        }

        //Ask the DAO to delete the vehicle from the database
        return vehicleDAO.deleteVehicle(vehicleId);
    }
}