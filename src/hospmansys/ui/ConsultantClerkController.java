/*
Class: ConsultantClerkController
Description: Bridge between the FXML portion of the application and the logic portion.
Created: 30/03/2020
Updated: 03/04/2020
Author/s: Michael Tonkin.
*/
package hospmansys.ui;

import hospmansys.ReferralReport;
import hospmansys.staff.ConsultantClerk;
import hospmansys.utils.PopupBox;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

public class ConsultantClerkController implements Initializable {
    
    @FXML private TableView<ReferralReport> reportTable;
    @FXML private TableColumn surnameCol;
    @FXML private TableColumn dobCol;
    @FXML private TableColumn rsCol;
    @FXML private TextArea expandedReportBox;
    @FXML private CheckBox checkSCS;
    @FXML private CheckBox checkPIC;
    @FXML private Button uploadBtn;

    
    private ObservableList reportsList()
    {
        List list = ConsultantClerk.getReports(); 
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
    
    /*
    Method: initialize
    Description: used to populate the gui.
    Parameters: All are handled by javaFX. Just leave it alone.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //initialize the referral reports table
        surnameCol.setCellValueFactory(new PropertyValueFactory<ReferralReport, String>("Surname"));
        dobCol.setCellValueFactory(new PropertyValueFactory<ReferralReport, String>("DoB"));
        rsCol.setCellValueFactory(new PropertyValueFactory<ReferralReport, String>("ReferralData"));
        reportTable.getItems().setAll(reportsList());        
        
       
    }
    /*
    Method: onRowSelected
    Description: handles when row in the referrals list is selected.
    */
    @FXML
    public void onRowSelected()
    {
     ReferralReport selectedProperty = reportTable.getSelectionModel().getSelectedItem();   
     
     expandedReportBox.setText(selectedProperty.getReferralData()); //put the referral into the box below.
    }
    
    /*
    Method: onUploadClicked
    Description: handles what happens when the upload button is clicked.
    */
    @FXML
    public void onUploadClicked() throws IOException
    {
        ReferralReport selectedProperty = reportTable.getSelectionModel().getSelectedItem(); //get the currently selected item
        
        if(selectedProperty == null) //if nothing is selected
        {
            PopupBox popup = new PopupBox("Failure! Please select a report and try again.");
        }
        else if(!(checkSCS.isSelected()) && !(checkPIC.isSelected())) //if only half the needed items are selected
        {
            PopupBox popup = new PopupBox("Failure! Please select either Surgery"
                    + " Clinic System or Patient Insurance Company or both.");
        }
        else //if everything is selected we can go ahead and upload
        {
            ConsultantClerk.saveReferral(selectedProperty, checkSCS.isSelected(), checkPIC.isSelected());
            PopupBox popup = new PopupBox("Upload successful!");
        }
    }
    
}
