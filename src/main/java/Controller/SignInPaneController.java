package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInPaneController implements Initializable {
    @FXML
    private Pane parent;
    @FXML
    private Pane TherapistApp;
    @FXML
    private Pane PatientApp;

    @FXML
    void onClickPatientApp(MouseEvent event) throws IOException {
        java.awt.Desktop.getDesktop().browse(URI.create("https://api.pcloud.com/getpubzip?code=kZa4bJXZnyL73ICKaCuG2sNMq3TyeQMAnQzk"));
    }

    @FXML
    void onClickTherapistApp(MouseEvent event) throws IOException {
        java.awt.Desktop.getDesktop().browse(URI.create("https://api.pcloud.com/getpubzip?code=kZDQbJXZfY0AKjjBdIHHI0Y8T6ERSSxVrljk"));


    }

    private void CssStyler() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.load(getClass().getResource("/FXML/Settings.fxml").openStream());
            SettingsController settingsController = loader.getController();
            parent.getStylesheets().removeAll();
            if (settingsController.isCustomeDesignFlag()) {
                String css = this.getClass().getResource("/Css/UserCustomDesign.css").toExternalForm();
                parent.getStylesheets().add(css);
            } else {
                if (settingsController.getToggleMode()) {
                    String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                    parent.getStylesheets().add(css);
                } else if (!settingsController.getToggleMode()) {
                    String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                    parent.getStylesheets().add(css);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CssStyler();


    }
}
