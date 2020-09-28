package Controller;

import DBH.userInfoDAO;
import Model.UserInfo;
import Util.MessageAlerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {

    DBH.userInfoDAO uiDAo= new userInfoDAO();


    @FXML
    private Pane parent;

    @FXML
    private Button BtnRegister;

    @FXML
    private TextField TextFieldRegisterUser;

    @FXML
    private TextField TextFieldRegisterPass;



    @FXML
    void OnClickBtnRegister(ActionEvent event) throws SQLException {
        UserInfo ui = new UserInfo(TextFieldRegisterUser.getText(),TextFieldRegisterPass.getText());
        uiDAo.inserUser(ui);
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
