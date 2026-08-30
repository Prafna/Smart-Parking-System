package parking.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

//controls the parking GUI
public class ParkingController {

    //show available parking slots
    @FXML
    public void showAvailableSlots(){

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Parking Slots");
        alert.setHeaderText("null");
        alert.setContentText("Available parking slots will be displayed here");
        alert.showAndWait();
    
    }
    
    //start a new parking session
    @FXML
    public void startParkingSession(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(" Parking Session");
        alert.setHeaderText("null");
        alert.setContentText("Parking session will start here.");
        alert.showAndWait();
    
    }
}
