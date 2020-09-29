package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class AboutController {

    @FXML
    private StackPane rootPane;

    @FXML
    private Button BtnCloseAbout;

    @FXML
    void onClickBtnCloseAbout(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
