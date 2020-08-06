package Controller;

import Util.FxmlLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane AnchorMainPane;
    @FXML
    public BorderPane BorderMainPane;
    @FXML
    private Button BtnNursing;
    @FXML
    private Button BtnPatient;
    @FXML
    private Button BtnMeals;
    @FXML
    private Button BtnMed;
    @FXML
    private Button BtnReports;
    @FXML
    private Button BtnStaff;
    @FXML
    private Button BtnConn;
    @FXML
    private Button BtnSett;




    @FXML
    void OnClickConn(ActionEvent event) {
    System.out.println("Workssssss");
    }

    @FXML
    void OnClickMeals(ActionEvent event) {

    }

    @FXML
    void OnClickMed(ActionEvent event) {

    }

    @FXML
    void OnClickNursing(ActionEvent event) throws IOException {
    System.out.println("Nursing Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("TherapistPane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickPatient(ActionEvent event) throws IOException {
        System.out.println("Patient Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("PatientManagementPane");
        BorderMainPane.setCenter(view);
    }

    @FXML
    void OnClickReports(ActionEvent event) {

    }

    @FXML
    void OnClickSettings(ActionEvent event) {

    }

    @FXML
    void OnClickStaff(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

   public void RemovePane(Pane pane){

        BorderMainPane.setCenter(pane);

    }
}
