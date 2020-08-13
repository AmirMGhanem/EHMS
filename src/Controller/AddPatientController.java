package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddPatientController {

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
    private ChoiceBox<?> ChoiceMedicine;

    @FXML
    private TextField TextFieldAllergyName;

    @FXML
    private Button BtnAdd;

    @FXML
    void OnClckBtnAdd(ActionEvent event) {

    }

}
