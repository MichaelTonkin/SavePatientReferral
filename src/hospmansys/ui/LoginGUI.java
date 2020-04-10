/*
Class: LoginGUI
Description: The controller class for the login screen ui. 
Created: 31/03/20
Updated: 10/04/20
Author/s: Michael Tonkin.
 */
package hospmansys.ui;

import hospmansys.staff.Login;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class LoginGUI extends Application {

    @FXML
    private Button loginBtn;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label loginError;
    
    private final int MAX_TIME_REMAINING = 2;
    private int timeRemaining = MAX_TIME_REMAINING;
    private Point oldLoc;
    private Point newLoc;
    private boolean newRound = true;
    private Stage window = new Stage();
    private Stage primaryStage;
    private boolean running = true;
    
    /*
    Method: start
    Description: launches the login screen
    Parameters: Stage primaryStage - required by javaFX. Leave it alone.
    */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LoginFXML.fxml"));
        
        
        primaryStage.setTitle("HospManSys Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        //ensure that all threads will stop when we close the window.
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        this.primaryStage = primaryStage;
        }


    public static void main(String[] args) {
        launch(args);
    }

    /*
    Method: onLoginClicked
    Description: Handles what happens when athe login button is clicked. 
    Parameters: ActionEvent e - identifies the click event.
    */
    @FXML
    public void onLoginClicked(ActionEvent e) throws IOException {
        
        loginBtn.getScene().getWindow().hide();
        
        //get the username and password from the gui.
        String usr = userField.getText();
        String pas = passField.getText();
        
        //check if the correct username and password has been used
        if (Login.userLogin(usr, pas) == 'c') {    //if we have the correct credentials        
            startCounter();//start the logout timer
            //launch the consultant clerk gui.
            Parent root = FXMLLoader.load(getClass().getResource("fxml/ConsultantClerkFXML.fxml"));
            Scene scene = new Scene(root);
            //set stage to window and get stage information

            window.setTitle("Patient Referral Reports");
            window.setScene(scene);
            window.setResizable(false);
            window.show();

            //ensure that all threads will stop when we close the window.
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    Platform.exit();
                    System.exit(0);
                }
            });
            
        } else if (Login.userLogin(usr, pas) == 'd') { //if wrong credentials are entered
            //display an error message to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Login failed! Please try again with the correct username and password.");

            alert.showAndWait();
        }

    }

    /*
    Method: returnToLogin
    Description: Called in the logout timer - ends consultant clerk session and returns to login
    */
    public void returnToLogin() {
        try {
            window.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("fxml/LoginFXML.fxml"));
            Scene scene = new Scene(root);
            //set stage to window and get stage information
            Stage window = new Stage();
            window.setTitle("Patient Referral Reports");
            window.setScene(scene);
            window.setResizable(false);
            window.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    /*
    Method: startCounter
    Description: Creates a new thread for handing the logout timer
    */
    public void startCounter() {
        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                runCounter();
            }
        };

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }
    
    /*
    Method: runCounter
    Description: handles logic for logout timer. See startCounter.
    */
    public void runCounter() {
        while(timeRemaining > -1 && running)
        {
            try {
                        
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //first run update old mouse lociation, second run update new mouse location
                        //these two variables will be comapred later
                        if (newRound) {
                                oldLoc = MouseInfo.getPointerInfo().getLocation();
                                newRound = false;
                            } else {
                                newLoc = MouseInfo.getPointerInfo().getLocation();
                                newRound = true;
                            }

                            //if the mouse has moved.
                            if (oldLoc.equals(newLoc)) {
                                timeRemaining -= 1; //keep counting down
                            } else {
                                timeRemaining = MAX_TIME_REMAINING; //reset the timer
                            }

                            System.out.println(timeRemaining);
                            System.out.println(oldLoc);
                            System.out.println(newLoc);
                            
                            //if enough time has elapsed without any mouse activity
                            if (timeRemaining == 0) {
                                    returnToLogin(); //logout.
                                    running = false; //stop the thread
                            }
                    }
                });

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

