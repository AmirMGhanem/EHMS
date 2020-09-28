package Controller;

import DBH.patientDAO;
import DBH.therapistDAO;
import DBH.userInfoDAO;
import Model.UserInfo;
import Util.FxmlLoader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private Button BtnLogIn;
    @FXML
    private TextField TextFieldUsername;
    @FXML
    private PasswordField TextFieldPassword;
    @FXML
    private ProgressBar ProgressBarLoading;
    @FXML
    public Label LabelLoading;
    ArrayList<UserInfo> users = new ArrayList<UserInfo>();
    DBH.userInfoDAO uiDAO = new userInfoDAO();
    //--------------------------------------------


    @FXML
    private AnchorPane AnchorMainPane;

    public AnchorPane getAnchorMainPane() {
        return AnchorMainPane;
    }

    public void setAnchorMainPane(AnchorPane anchorMainPane) {
        AnchorMainPane = anchorMainPane;
    }

    @FXML
    private BorderPane BorderMainPane;

    @FXML
    private Button BtnNursing;
    @FXML
    private Button BtnPatient;
    @FXML
    private Button BtnMeals;
    @FXML
    private Button BtnMed;
    @FXML
    private Button BtnCrudMed;
    @FXML
    private Button BtnMeeting;
    @FXML
    private Button BtnConn;
    @FXML
    private Button BtnSett;
    @FXML
    private ImageView LogoHome;
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
    private Label LabelClock;
    @FXML
    private Pane panelLogin;

    Node loginpane;

    final javafx.concurrent.Service thread = new javafx.concurrent.Service<Integer>() {
        @Override
        public Task createTask() {
            return new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    int i;
                    for (i = 0; i < 250; i++) {
                        updateProgress(i, 250);
                        Thread.sleep(1);

                    }
                    return i;
                }
            };
        }
    };


    @FXML
    void OnClickLogin(ActionEvent event) throws InterruptedException, IOException {

                                for (UserInfo ui : users) {
                                    if (ui.getUsername().equals(TextFieldUsername.getText()) && ui.getPassword().equals(TextFieldPassword.getText())) {
                                        ProgressBarLoading.setVisible(true);
                                        LabelLoading.setVisible(true);
                                        ManualSetOpenNav();
                                        setEnableAllButtons();
                                        ProgressBarLoading.progressProperty().bind(thread.progressProperty());
                                        thread.start();
                                        thread.workDoneProperty().addListener(new ChangeListener<Number>() {
                                            @Override
                                            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                                                if (thread.workDoneProperty().get() > 248.9) {
                                                    try {
                            thread.cancel();
                                OpenDashBoardManual();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });


            }
        }

    }

    public void OnClickEHMS(MouseEvent event)throws IOException{
        System.out.println("Dashboard Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("SignInPane");
        BorderMainPane.setCenter(view);
    }

    public void OpenDashBoardManual() throws IOException {
        System.out.println("Dashboard Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("SignInPane");
        BorderMainPane.setCenter(view);
    }


    private void clockTicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        final Calendar cal = Calendar.getInstance();
                        LabelClock.setText(sdf.format(cal.getTime()));
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void onClickBtnLogout() throws InterruptedException {
        BorderMainPane.setCenter(loginpane);
        TextFieldUsername.setText("");
        TextFieldPassword.setText("");
        setDisableAllButtons();
        ManualSetCloseNav();
        thread.reset();
        ProgressBarLoading.progressProperty().unbind();

    }


    @FXML
    void onClickBtnExitProject() throws IOException {

        //MainPaneController.TerminateThread();
        Platform.exit();
        System.exit(0);
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

    private void ManualSetCloseNav() throws InterruptedException {
        TranslateTransition mancloseNav;
        mancloseNav = new TranslateTransition(new Duration(666), NavBox);
        mancloseNav.setToX(-(265));
        mancloseNav.play();
    }

    private void ManualSetOpenNav() throws InterruptedException {
        TranslateTransition manopenNav;
        manopenNav = new TranslateTransition(new Duration(666), NavBox);
        manopenNav.setToX(0);
        manopenNav.play();
    }

    private void drawerAction() {
        openNav = new TranslateTransition(new Duration(666), NavBox);
        openNav.setToX(0);
        closeNav = new TranslateTransition(new Duration(666), NavBox);
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

    @FXML
    public void mouseEnterpanelLogin(MouseEvent event) throws InterruptedException {
        ManualSetOpenNav();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginpane = BorderMainPane.getCenter();
            setDisableAllButtons();
            setDisableAllButtons();
            clockTicker();
            ManualSetCloseNav();
            drawerAction();
            users = uiDAO.SelectAll();
            System.out.println(users);
            ProgressBarLoading.setVisible(false);
            LabelLoading.setVisible(false);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setCenter(Pane pane) {
        BorderMainPane.setCenter(pane);
    }


    public void DarkModeHandle() {
        String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
        BorderMainPane.getStylesheets().add(css);
    }

    public void LightModeHandle() {
        String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
        BorderMainPane.getStylesheets().add(css);
    }

    public void setDisableAllButtons() {
        BtnAddNurse.setDisable(true);
        BtnEditNurse.setDisable(true);
        BtnAddPatient.setDisable(true);
        BtnEditPatient.setDisable(true);
        BtnPatient.setDisable(true);
        BtnMeals.setDisable(true);
        BtnMed.setDisable(true);
        BtnMeeting.setDisable(true);
        BtnConn.setDisable(true);
        BtnSett.setDisable(true);
        LogoHome.setDisable(true);
        BtnNursing.setDisable(true);
        BtnCrudMed.setDisable(true);
    }

    public void setEnableAllButtons() {

        BtnAddNurse.setDisable(false);
        BtnEditNurse.setDisable(false);
        BtnAddPatient.setDisable(false);
        BtnEditPatient.setDisable(false);
        BtnPatient.setDisable(false);
        BtnMeals.setDisable(false);
        BtnMed.setDisable(false);
        BtnMeeting.setDisable(false);
        BtnConn.setDisable(false);
        BtnSett.setDisable(false);
        LogoHome.setDisable(false);
        BtnNursing.setDisable(false);
        BtnCrudMed.setDisable(false);
    }


}
