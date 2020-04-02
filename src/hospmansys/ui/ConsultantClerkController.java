
package hospmansys.ui;

import hospmansys.ReferralReport;
import hospmansys.staff.ConsultantClerk;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML private ComboBox permissionsSelect;
    @FXML private Button uploadBtn;
    private CheckBox option1 = new CheckBox("Patient Insurance Company #1");
    private CheckBox option2 = new CheckBox("Patient Insurance Company #2");
    private CheckBox option3 = new CheckBox("Surgery Clinic");
    
    
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
    public void onUploadClicked()
    {
        ReferralReport selectedProperty = reportTable.getSelectionModel().getSelectedItem();
        if(selectedProperty == null)
        {
            System.out.println("Null selectedProperty ln 78");
        }
        else
        {
            ConsultantClerk.saveReferral(selectedProperty, "null");
        }
    }
    
}
