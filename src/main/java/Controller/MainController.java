package Controller;

import DBH.patientDAO;
import DBH.therapistDAO;
import Util.FxmlLoader;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML private AnchorPane AnchorMainPane;
    @FXML public BorderPane BorderMainPane;
    @FXML private Button BtnNursing;
    @FXML private Button BtnPatient;
    @FXML private Button BtnMeals;
    @FXML private Button BtnMed;
    @FXML private Button BtnReports;
    @FXML private Button BtnMeeting;
    @FXML private Button BtnConn;
    @FXML private Button BtnSett;
    @FXML private ImageView LogoHome;
    TranslateTransition openNav;     //IMAGEVIEW TRANSITION
    TranslateTransition closeNav;    //IMAGEVIEW TRANSITION
    @FXML
    private VBox NavBox;
    @FXML
    private Button btest;
    @FXML
    private ImageView ImageSlide;
    @FXML
    private Button BtnAddNurse;
    @FXML
    private Button BtnEditNurse;
    @FXML
    private Button BtnAddPatient;
    @FXML
    private Button BtnEditPatient;
    @FXML
    private Button BtnExitProject;
    @FXML
    private Button BtnLogout;


    @FXML
    void onClickBtnLogout() throws IOException {
        System.out.println("Dashboard Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("SignInPane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void onClickBtnExitProject() throws IOException {

       // FXMLLoader loader = new FXMLLoader();
       // Pane root = loader.load(getClass().getResource("/FXML/MainPane.fxml").openStream());
       // MainPaneController mainPaneController= (MainPaneController)loader.getController();
       // mainPaneController.TerminateThread();
        Platform.exit();
    }

    @FXML
    void OnMouseClickedSlide(MouseEvent event) {
        if (NavBox.getTranslateX() != 0) {
            BorderMainPane.setLeft(NavBox);
            openNav.play();
        } else {
            closeNav.setToX(-(NavBox.getWidth()) + 50);
            closeNav.play();
        }

    }
    private void drawerAction() {
        openNav = new TranslateTransition(new Duration(350), NavBox);
        openNav.setToX(0);
        closeNav = new TranslateTransition(new Duration(350), NavBox);
    }

    @FXML
    void LogoHomeClicked(MouseEvent event) throws IOException {
        System.out.println("LOGOOOOOOOO Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("MainPane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickConn(ActionEvent event) throws IOException {

        System.out.println("Connection Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ConnectionPane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickMeals(ActionEvent event) throws IOException {
        System.out.println("Meals Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Meals");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickMed(ActionEvent event) throws IOException {
        System.out.println("Medicine Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("MedicinePane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickNursing(ActionEvent event) throws IOException, SQLException {

        TherapistPaneController tpc = new TherapistPaneController();
        ObservableList obTherapist = FXCollections.observableArrayList();

        DBH.therapistDAO tdo = new therapistDAO();
        obTherapist = tdo.selectTherapists();

        System.out.println("Nursing Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("TherapistPane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickPatient(ActionEvent event) throws IOException, SQLException {
        PatientPaneController ppc = new PatientPaneController();
        ObservableList obPatient = FXCollections.observableArrayList();

        DBH.patientDAO pdo = new patientDAO();
        obPatient = pdo.selectPatients();
        System.out.println("==========" + obPatient);

        System.out.println("Patient Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("PatientManagementPane");
        BorderMainPane.setCenter(view);

    }

    @FXML
    void OnClickReports(ActionEvent event) throws IOException {
        System.out.println("Reports Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Reports");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickSettings(ActionEvent event) throws IOException {

        System.out.println("Settings Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Settings");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickMeeting(ActionEvent event) throws IOException {
        System.out.println("meeting Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("meeting");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickAddNurse(ActionEvent event) throws IOException {
        System.out.println("EditNurse Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("addNurse");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickAddPatient(ActionEvent event) throws IOException {

        System.out.println("Add Patient Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("addPatient");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickCrudMed(ActionEvent event) throws IOException {

        System.out.println("CRUD Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("AddMedPane");
        BorderMainPane.setCenter(view);
    }


    @FXML
    void OnClickEditNurse(ActionEvent event) throws IOException {
        System.out.println("EditNurse Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("editNurse");
        BorderMainPane.setCenter(view);
    }


    @FXML
    void OnClickEditPatient(ActionEvent event) throws IOException {

        System.out.println("Add Patient Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("editPatient");
        BorderMainPane.setCenter(view);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FxmlLoader object = new FxmlLoader();
        Pane view2 = null;
        try {
            view2 = object.getPage("SignInPane");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BorderMainPane.setCenter(view2);
        drawerAction();

    }

    public void setCenter(Pane pane) {
        BorderMainPane.setCenter(pane);
    }


}
