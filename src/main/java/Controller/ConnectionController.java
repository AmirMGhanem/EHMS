package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {


    @FXML
    private Pane parent;

    @FXML
    private Button BtnRegister;

    @FXML
    private TextField TextFieldRegisterUser;

    @FXML
    private TextField TextFieldRegisterPass;

    @FXML
    private TextField TextFieldRegisterID;

    @FXML
    private Button BtnCheck;

    @FXML
    private TextField TextFieldUserName;

    @FXML
    private TextField TextFieldPassword;

    @FXML
    private Button BtnEstablishConnection;

    @FXML
    private TextField TextFieldDataBase;

    @FXML
    void OnClickBtnCheck(ActionEvent event) {

    }

    @FXML
    void OnClickBtnRegister(ActionEvent event) {

    }

    @FXML
    void OnClickEstablishConn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CssStyler();
    }

    private void CssStyler() {
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.load(getClass().getResource("/FXML/Settings.fxml").openStream());

            SettingsController settingsController = loader.getController();

            if (settingsController.getToggleMode()) {
                String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            } else {
                String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                parent.getStylesheets().add(css);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
