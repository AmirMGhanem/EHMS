package Controller;

import DBH.patientDAO;
import DBH.therapistDAO;
import DBH.userInfoDAO;
import Model.UserInfo;
import Util.*;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.stage.Window;
import javafx.util.Duration;
import java.awt.*;
import java.io.File;
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
    MessageAlerter messageAlerter = new MessageAlerter();
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
    @FXML
    private MenuItem BtnScreenshot;
    @FXML
    private MenuItem BtnExportCss;
    @FXML
    private MenuItem lightmode;
    @FXML
    private MenuItem darkmode;
    @FXML
    private MenuItem customdesign;
    @FXML
    private Label LabelDashboard;


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
    void OnClickLogin(ActionEvent event) throws InterruptedException, IOException, SQLException, ClassNotFoundException {


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

    public void OnClickEHMS(MouseEvent event) throws IOException {
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
        System.out.println("Nursing Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("TherapistPane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickPatient(ActionEvent event) throws IOException, SQLException {
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


    public void setDisableAllButtons() {
        BtnAddNurse.setDisable(true);
        BtnEditNurse.setDisable(true);
        BtnAddPatient.setDisable(true);
        BtnEditPatient.setDisable(true);
        BtnPatient.setDisable(true);
        BtnMeals.setDisable(true);
        BtnMed.setDisable(true);
        BtnMeeting.setDisable(true);
        BtnSett.setDisable(true);
        LogoHome.setDisable(true);
        BtnNursing.setDisable(true);
        BtnCrudMed.setDisable(true);
        LabelDashboard.setDisable(true);
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
        BtnSett.setDisable(false);
        LogoHome.setDisable(false);
        BtnNursing.setDisable(false);
        BtnCrudMed.setDisable(false);
        LabelDashboard.setDisable(false);
    }

    @FXML
    public void onClickBtnScreenshot(ActionEvent event) throws IOException, AWTException {


        MenuItem menuItem = (MenuItem) event.getTarget();
        ContextMenu cm = menuItem.getParentPopup();
        Scene scene = cm.getScene();
        Window window = scene.getWindow();
        PdfExporter pdfExporter = new PdfExporter();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(window);

        if (selectedDirectory != null) {
            pdfExporter.ScreenShot(selectedDirectory.getAbsolutePath());
            System.out.println(selectedDirectory.getAbsolutePath());
        } else {
            messageAlerter.ShowErrorMessage("ERROR!!!", "Directory Path is null", "***Please Choose A Directory in order\n to save the Project Screenshot");
        }

    }

    @FXML
    public void onClickBtnEditDatabaseInfo(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("DatabaseInformation");
        Stage stage = new Stage();
        stage.setTitle("Db info Window");
        stage.setScene(new Scene(view, 737, 445));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    public void onClickBtnAbout(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("about");
        Stage stage = new Stage();
        stage.setTitle("About Window");
        stage.setScene(new Scene(view, 820, 470));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    public void onClickBtnExportCss(ActionEvent event) throws IOException {
        Service s = new Service();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        File source = new File("src/main/resources/Css/lightmode.css");
        File dest = new File(selectedDirectory.getPath() + "/UserCustomDesign.css");
        if (selectedDirectory != null) {
            s.copyFileUsingChannel(source, dest);
        } else {
            messageAlerter.ShowErrorMessage("ERROR!!!", "Directory Path is null", "***Please Choose A Directory in order\n to save the LightMode css Template ");
        }
    }

    @FXML
    public void onClickBtnimportcss(ActionEvent event) throws IOException {
        Service s = new Service();
        MenuItem menuItem = (MenuItem) event.getTarget();
        ContextMenu cm = menuItem.getParentPopup();
        Scene scene = cm.getScene();
        Window window = scene.getWindow();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSS Files", "*.css"));
        File selectedFile = fc.showOpenDialog(window);
        fc.setInitialFileName("UserCustomDesign.css");
        File dest = new File("src/main/resources/Css/UserCustomDesign.css");
        s.copyFileUsingChannel(selectedFile, dest);

        //messageAlerter.ShowErrorMessage("File Not Found", "Please Choose A File", "Please Choose a file \n (*.css) in order to import")

    }

    @FXML
    public void onclickdark(ActionEvent event) throws IOException {
        Desktop desktop=Desktop.getDesktop();
        File f = new File("src/main/resources/Css/darkmode.css");
        desktop.open(f);
    }

    @FXML
    public void onclicklight(ActionEvent event) throws IOException {
        Desktop desktop=Desktop.getDesktop();
        File f = new File("src/main/resources/Css/lightmode.css");
        desktop.open(f);
    }

    @FXML
    public void onclickcustom(ActionEvent event) throws IOException {
        Desktop desktop=Desktop.getDesktop();
        File f = new File("src/main/resources/Css/UserCustomDesign.css");
        desktop.open(f);
    }
}
