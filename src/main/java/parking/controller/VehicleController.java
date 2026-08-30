package parking.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

//controls the vehicles GUI
public class VehicleController {

    //CALLED WHEN REGISTER VEHICLE BUTTON IS CLICKED
    @FXML
    public void registerVehicle(){

        //temporary information message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("vehicle Registration");
        alert.setHeaderText("null");
        alert.setContentText("Vehicle registration will be connected to the vehicleDAO.");
        alert.showAndWait();
    
    }
    
}