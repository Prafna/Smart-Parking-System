package parking.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//controls the login GUI
public class LoginController 
{
    //connect with the username  text fields in the login.fxml file
    @FXML
    private TextField usernameField;

    //connect with the password text fields in the login.fxml file
    @FXML
    private PasswordField passwordField;

    //this method is calling when the user click the login button
    @FXML
    private void login(){

        //get username enter by the user
        String username = usernameField.getText();

        //get password enter by the user
        String password = passwordField.getText();

        //temporary login validation
        if(username.equals("admin")&&password.equals("1234")){

            //load dashboard page
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));

                //get current application window
                Stage stage = (Stage) usernameField.getScene().getWindow();

                //change scene to dashboard
                stage.setScene(new Scene(root));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            //show error message if login failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("null");
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
        }
        
    }
}
