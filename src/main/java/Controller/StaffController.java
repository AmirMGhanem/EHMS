package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffController implements Initializable,Util.JavafxPaneHandler {

    @FXML
    private ChoiceBox<?> ChoiceShowEmployeeID;

    @FXML
    private TextArea TextAreaShow;

    @FXML
    private ChoiceBox<?> ChoiceEditEmployeeID;

    @FXML
    private Button BtnClear;

    @FXML
    private ChoiceBox<?> ChoiceDeleteEmployeeID;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnAdd;

    @FXML
    private TextField TextFieldFirstName;

    @FXML
    private TextField TextFieldLastName;

    @FXML
    private ChoiceBox<?> ChoiceGender;

    @FXML
    private DatePicker DatePickerBirthDate;

    @FXML
    private TextField TextFieldID;

    @FXML
    private ChoiceBox<?> ChoiceRole;

    @FXML
    private TextField TextFieldContactNum;

    @FXML
    private ChoiceBox<?> Choice3Digits;

    @FXML
    private TextField TextFieldCity;

    @FXML
    private TextField TextFieldStreet;

    @FXML
    private TextField TextFieldHouseNum;

    @FXML
    void OnClckBtnAdd(ActionEvent event) {

    }

    @FXML
    void OnClickBtnClear(ActionEvent event) {

    }

    @FXML
    void OnClickBtnDelete(ActionEvent event) {

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
