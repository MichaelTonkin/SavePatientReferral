/*
Class: LoginGUI
Description: The controller class for the login screen ui. 
Created: 31/03/20
Updated: 01/04/20
Author/s: Michael Tonkin.
*/
package hospmansys.ui;

import hospmansys.staff.Login;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class LoginGUI extends Application {
    
    @FXML private Button loginBtn;
    @FXML private TextField userField;
    @FXML private PasswordField passField;
    @FXML private Label loginError;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("fxml/LoginFXML.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("fxml/ConsultantClerkFXML.fxml"));
        primaryStage.setTitle("HospManSys Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    public void onLoginClicked(ActionEvent e) throws IOException 
    {
        String usr = userField.getText();
        String pas = passField.getText();
        System.out.println(usr);
        if(Login.userLogin(usr, pas) == 'c')
        {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/ConsultantClerkFXML.fxml"));
        Scene scene = new Scene(root);
        //set stage to window and get stage information
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Patient Referral Reports");
        window.setScene(scene);
        window.show();
        }
        else if (Login.userLogin(usr, pas) == 'd')
        {
            loginError.setText("Incorrect username or password. Please re-enter login information and try again.");
        }
        
    }
    
}
