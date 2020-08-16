package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPatientController implements Initializable,Util.JavafxPaneHandler {

    @FXML private TextField TextFieldFirstName;
    @FXML private TextField TextFieldLastName;
    @FXML private TextField TextFieldCity;
    @FXML private TextField TextFieldContactNum;
    @FXML private ChoiceBox<?> Choice3Digits;
    @FXML private TextField TextFieldStreet;
    @FXML private TextField TextFieldHouseNum;
    @FXML private TextField TextFieldAddAllergyName;
    @FXML private ChoiceBox<?> ChoiceMedicine;
    @FXML private ChoiceBox<?> ChoiceDeleteAllergyName;
    @FXML private Button BtnClear;
    @FXML private Button BtnSave;
    @FXML private ChoiceBox<?> ChoiceNurse;
    @FXML private Label LblNurseID;


    @FXML
    void OnClickBtnClear(ActionEvent event) {

    }

    @FXML
    void OnClickBtnSave(ActionEvent event) {

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
