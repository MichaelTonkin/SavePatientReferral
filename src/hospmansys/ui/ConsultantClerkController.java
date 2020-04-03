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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //initialize the referral reports table
        surnameCol.setCellValueFactory(new PropertyValueFactory<ReferralReport, String>("Surname"));
        dobCol.setCellValueFactory(new PropertyValueFactory<ReferralReport, String>("DoB"));
        rsCol.setCellValueFactory(new PropertyValueFactory<ReferralReport, String>("ReferralData"));
        reportTable.getItems().setAll(reportsList());        
    }
    
    @FXML
    public void onRowSelected()
    {
     ReferralReport selectedProperty = reportTable.getSelectionModel().getSelectedItem();   
     
     expandedReportBox.setText(selectedProperty.getReferralData());
    }
    
    @FXML
    public void onUploadClicked() throws IOException
    {
        ReferralReport selectedProperty = reportTable.getSelectionModel().getSelectedItem();
        if(selectedProperty == null)
        {
            PopupBox popup = new PopupBox("Failure! Please select a report and try again.");
        }
        else if(!(checkSCS.isSelected()) && !(checkPIC.isSelected()))
        {
            PopupBox popup = new PopupBox("Failure! Please select either Surgery"
                    + " Clinic System or Patient Insurance Company or both.");
        }
        else
        {
            ConsultantClerk.saveReferral(selectedProperty, checkSCS.isSelected(), checkPIC.isSelected());
            PopupBox popup = new PopupBox("Upload successful!");
        }
    }
    
}
