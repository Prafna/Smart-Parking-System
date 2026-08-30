package parking.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//control the dashboard GUI

public class DashboardController {

    //open vehicle management page
    @FXML
    public void openVehiclePage(){
        openPage("/vehicle.fxml");

    }

    //OPEN PARKING MANAGEMENT PAGE
    @FXML
    public void openParkingPage(){
        openPage("/parking.fxml");
    }

    //open payment management page
    @FXML
    public void openPaymentPage(){
        openPage("/payment.fxml");
    }

    //reusable method to open a new page
    private void openPage(String fxmlFile){
        try {
            //load selected fxml page
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

            //get current application window
            Stage stage = (Stage)Stage.getWindows().stream().filter(window -> window.isShowing()).findFirst().orElse(null);


            //change scene if window exist
            if(stage != null){
                stage.setScene(new Scene(root));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}