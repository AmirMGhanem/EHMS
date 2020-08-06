package Controller;

import Util.FxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TherapistPaneController {

    @FXML
    private TableView<?> NurseTable;

    @FXML
    private Button BtnAddNurse;

    @FXML
    private Button BtnRemoveNurse;

    @FXML
    private Button BtnEditNurse;

    @FXML
    private Button BtnNurseInvest;

    @FXML
    private Button BtnNurseFile;

    @FXML
    private Button BtnNurseXML;

    @FXML
    private Button BtnBack;

    @FXML
    void OnClickAdd(ActionEvent event) {

    }

    @FXML
    void OnClickBack(ActionEvent event) throws IOException {

        System.out.println("Back Clicked");


        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("PatientManagementPane");


    }

    @FXML
    void OnClickEdit(ActionEvent event) {

    }

    @FXML
    void OnClickInvestigation(ActionEvent event) {

    }

    @FXML
    void OnClickRemove(ActionEvent event) {

    }

    @FXML
    void OnClickToFile(ActionEvent event) {

    }

    @FXML
    void OnClickToXML(ActionEvent event) {

    }



}
