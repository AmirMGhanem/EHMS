package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable,Util.JavafxPaneHandler {

    @FXML private Button BtnRegister;
    @FXML private TextField TextFieldRegisterUser;
    @FXML private TextField TextFieldRegisterPass;
    @FXML private TextField TextFieldRegisterID;
    @FXML private Button BtnCheck;


    @FXML
    void OnClickBtnCheck(ActionEvent event) {

    }

    @FXML
    void OnClickBtnRegister(ActionEvent event) {

    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() {

    }

    @Override
    public void JavafxDiagramFill() {

    }
}
