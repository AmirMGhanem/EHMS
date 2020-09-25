package Controller;

import DBH.patientDAO;
import Util.FxmlLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.jboss.jandex.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class SignInPaneController implements Initializable {

    @FXML private Pane parent;
    @FXML
    private Button BtnLogIn;

    @FXML
    private TextField TextFieldUsername;

    @FXML
    private PasswordField TextFieldPassword;

    @FXML
    private CheckBox CheckBoxIsAdmin;

    @FXML
    private ImageView ImageViewTherapistPane;

    @FXML
    private ImageView ImageViewTherapistExportPDF1;

    @FXML
    private ImageView ImageViewTherapistExportPDF2;

    @FXML
    private ImageView ImageViewTherapistExportFile1;

    @FXML
    private ImageView ImageViewTherapistExportFile2;

    @FXML
    private ImageView ImageViewPatientPane;

    @FXML
    private ImageView ImageViewPatientExportPDF1;

    @FXML
    private ImageView ImageViewPatientExportPDF2;

    @FXML
    private ImageView ImageViewPatientExportFile1;

    @FXML
    private ImageView ImageViewPatientExportFile2;

    @FXML
    private ProgressBar ProgressBarLoading;

    @FXML
    private Label LabelLoading;



    @FXML
    void OnClickLogin(ActionEvent event) throws InterruptedException {

    ProgressBarLoading.setVisible(true);
    LabelLoading.setVisible(true);


    }

    @FXML
    void PatientFileClick(MouseEvent event) throws IOException, SQLException {
        PatientPaneController ppc = new PatientPaneController();
        ObservableList obPatient = FXCollections.observableArrayList();

        DBH.patientDAO pdo = new patientDAO();
        obPatient = pdo.selectPatients();
        System.out.println("==========" + obPatient);

        FXMLLoader loader = new FXMLLoader();
        MainController bpc = (MainController) loader.getController();
        System.out.println("Patient Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("PatientManagementPane");
        bpc.BorderMainPane.setCenter(view);
    }

    @FXML
    void PatientPDFClick(MouseEvent event) {

    }

    @FXML
    void PatientPaneClick(MouseEvent event) {

    }

    @FXML
    void TherapistFileClick(MouseEvent event) {

    }

    @FXML
    void TherapistPDFClick(MouseEvent event) {

    }

    @FXML
    void TherapistPaneClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CssStyler();
        ProgressBarLoading.setVisible(false);
        LabelLoading.setVisible(false);
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
