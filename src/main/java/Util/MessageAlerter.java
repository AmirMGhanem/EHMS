package Util;

import javafx.scene.control.Alert;

public class MessageAlerter {
    public void ShowWarningMessage(String title, String header, String Content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(Content);
        alert.showAndWait();
    }

    public void ShowErrorMessage(String title, String header, String Content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(Content);
        alert.showAndWait();
    }


    public void MessageWithoutHeader(String title, String Content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(Content);
        alert.showAndWait();
    }

}

