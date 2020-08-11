package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddNurseController {

    @FXML
    private TextField TextFieldFirstName;

    @FXML
    private TextField TextFieldLastName;

    @FXML
    private TextField TextFieldID;

    @FXML
    private ChoiceBox<?> ChoiceGender;

    @FXML
    private DatePicker DatePickerBirthdate;

    @FXML
    private TextField TextFieldContactNum;

    @FXML
    private ChoiceBox<?> Choice3DigitsNum;

    @FXML
    private TextField TextFieldCity;

    @FXML
    private TextField TextFieldStreet;

    @FXML
    private TextField TextFieldHouseNum;

    @FXML
    private Button BtnAdd;

    @FXML
    void OnClickAdd(ActionEvent event) {

    }

}
