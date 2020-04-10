/*
Class: PopupBox
Description: generic utility to generate a popup box.
*/
package hospmansys.utils;

import javafx.scene.control.Alert;

public class PopupBox {
 
    public PopupBox(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }
    
}
